package com.standard.security;



import com.standard.entity.security.UserEntity;
import com.standard.entity.security.UserSessionEntity;
import com.standard.repository.security.UserSessionRepository;
import com.standard.security.utils.HashUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class RegisterSessionAuthenticationStrategy implements SessionAuthenticationStrategy {

    private final UserSessionRepository userSessionRepository;

    @Override
    @Transactional
    public void onAuthentication(Authentication authentication,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
                                    throws SessionAuthenticationException {

        int concurrentSessions = getConcurrentSessions();
        String userId = getUserId(authentication);
        if (userId != null) {
            log.debug("Registering the last SessionId for userId {}", userId);
            try {
                registerNewSession(request, concurrentSessions, userId);
            } catch (RuntimeException e) {
                if (e instanceof OptimisticLockingFailureException) {
                    log.warn("(optimistic Locking) unable to register the logged in session({})", userId);
                }
                log.warn("Update of SessionId for userId {} fails", userId, e);
                throw new SessionAuthenticationException("Update of SessionId for userId " + userId + " fails. Message=" + e.getMessage());
            }
        }
    }

    @Transactional
    public void registerNewSession(HttpServletRequest request, int concurrentSessions, String userId) {
        UserSessionEntity userSession = getUserAttribute(userId);
        String value = buildNewValue(request, concurrentSessions, userSession.getActiveSessions());
        userSession.setActiveSessions(value);
        userSessionRepository.saveAndFlush(userSession);
    }

    private UserSessionEntity getUserAttribute(String userId) {
        UserSessionEntity userAttribute = new UserSessionEntity();
        Optional<UserSessionEntity> userAttributeOpt = userSessionRepository.findById(userId);
        if (userAttributeOpt.isEmpty()) {
            userAttribute.setUserId(userId);
        } else {
            userAttribute = userAttributeOpt.get();
        }
        return userAttribute;
    }

    private int getConcurrentSessions() {
        int concurrentUsers = -1;
        //Development can define the authorized number of logins.
        try {
            String concurrentValue = "1";
            concurrentUsers = Integer.parseInt(concurrentValue);
        } catch (Exception e) {
            log.error("Error retrieving concurrent users {}", "CONCURRENT_SESSIONS", e);
        }
        return concurrentUsers;
    }

    private String getUserId(Authentication authentication) {
        String userId = ((UserEntity) authentication.getPrincipal()).getUsername();
        log.warn("received Principal {} not registered as user", authentication.getPrincipal());
        return userId;
    }

    private String buildNewValue(HttpServletRequest request, int concurrentSessions, String oldValue) {
        String value = String.valueOf(HashUtils.hashString(request.getSession(false).getId()));
        if (oldValue == null) {
            oldValue = "";
        }
        if (concurrentSessions > 1) {
            String[] keys = StringUtils.commaDelimitedListToStringArray(oldValue);
            String[] newkeys = new String[concurrentSessions];
            if (keys.length < concurrentSessions) {
                newkeys[keys.length] = value;
                System.arraycopy(keys, 0, newkeys, 0, keys.length);
            } else {
                // Remove the first (more older) logging key.
                newkeys[concurrentSessions - 1] = value;
                System.arraycopy(keys, 1, newkeys, 0, concurrentSessions - 1);
            }
            value = StringUtils.arrayToCommaDelimitedString(newkeys);
        }
        return value;
    }
}
