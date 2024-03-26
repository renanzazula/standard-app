package com.standard.security.filter;


import com.standard.entity.security.UserSessionEntity;
import com.standard.repository.security.UserSessionRepository;
import com.standard.security.utils.HashUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class ConcurrentSessionFilter extends GenericFilterBean {

    private final UserSessionRepository userSessionRepository;

    private static final String EXPIRED_URL = "/session/invalid";
    private final LogoutHandler[] handlers = new LogoutHandler[]{new SecurityContextLogoutHandler()};
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public ConcurrentSessionFilter(UserSessionRepository userSessionRepository) {
        this.userSessionRepository = userSessionRepository;
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        if (controlConcurrentSessions() && session != null) {
            final Authentication current = SecurityContextHolder.getContext().getAuthentication();
            if (current != null && !isInConcurrentSessionsRegistered(current.getName(), session.getId())) {
                doLogout(request, response);
                redirectStrategy.sendRedirect(request, response, EXPIRED_URL);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void afterPropertiesSet() {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(EXPIRED_URL), EXPIRED_URL + " isn't a valid redirect URL");
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (LogoutHandler handler : handlers) {
            handler.logout(request, response, auth);
        }
    }

    /**
     * Method to retrieve if is necessary control concurrent Sessions of same
     * user.
     *
     * @return true if the filter must act.
     */
    private boolean controlConcurrentSessions() {
        boolean concurrentUsers = true;
        try {
            String concurrentValue = "1";
            concurrentUsers = Integer.valueOf(concurrentValue.trim()) > 0;
        } catch (Exception e) {
            log.error("Error retrieving concurrent users {}", e);
        }
        return concurrentUsers;
    }

    /**
     * Method for validate if the currentSession of the Principal is in the Last
     * Concurrent Sessions queue authorized
     */
    private boolean isInConcurrentSessionsRegistered(String userId, String sessionId) {
        boolean resp = false;
        Optional<UserSessionEntity> userAttribute = userSessionRepository.findById(userId);
        if (userAttribute.isPresent()) {
            String value = (userAttribute.get().getActiveSessions() == null) ? "" : userAttribute.get().getActiveSessions();
            String idReceived = String.valueOf(HashUtils.hashString(sessionId));
            if (StringUtils.commaDelimitedListToSet(value).contains(idReceived)) {
                resp = true;
            } else {
                log.error("Authenticated user {} with an older session", userId);
            }
        } else {
            resp = true;
            log.warn("Authenticated user {} without session registration evidence !!!", userId);
        }
        return resp;
    }
}
