package com.standard.security;


import com.standard.enums.ConfigParamsEnum;
import com.standard.service.configparam.ConfigParamService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Slf4j
@AllArgsConstructor
public class TimeoutAuthenticationStrategy implements SessionAuthenticationStrategy {

    private final ConfigParamService configParamService;
    private static final Integer DEFAULT_SESSION_TIMEOUT = 60;

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws SessionAuthenticationException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setMaxInactiveInterval(retrieveSessionTimeout());
        }
    }

    private Integer retrieveSessionTimeout() {
        try {
            return configParamService
                    .getParameterValue(ConfigParamsEnum.SESSION_TIMEOUT, Integer.class)
                    .filter(timeout -> timeout > 0)
                    .orElse(DEFAULT_SESSION_TIMEOUT) * 60;
        } catch (Exception e) {
            log.error("Error retrieving session timeout, using default value: {}", DEFAULT_SESSION_TIMEOUT, e);
            return DEFAULT_SESSION_TIMEOUT * 60;
        }
    }


}
