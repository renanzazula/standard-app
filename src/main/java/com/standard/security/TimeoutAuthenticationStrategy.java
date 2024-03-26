package com.standard.security;


import com.standard.enums.ConfigParamsEnum;
import com.standard.service.configparam.ConfigParamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class TimeoutAuthenticationStrategy implements SessionAuthenticationStrategy {

    private final ConfigParamService configParamService;
    private static final Integer DEFAULT_SESSION_TIMEOUT = 60;

    @Override
    public void onAuthentication(Authentication authentication, HttpServletRequest request, HttpServletResponse response) throws SessionAuthenticationException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.setMaxInactiveInterval(retrieveSessionTimeOut());
        }
    }

    private Integer retrieveSessionTimeOut() {
        Integer timeout = null;
        try {
            Optional<Integer> timeoutValue = configParamService.getParameterValue(ConfigParamsEnum.SESSION_TIMEOUT, Integer.class);
            timeout = timeoutValue.get();
        } catch (Exception e) {
            log.error("Error retrieving session timeout", e);
        }
        if (null == timeout || timeout <= 0) {
            timeout = DEFAULT_SESSION_TIMEOUT;
        }
        return timeout * 60;

    }

}
