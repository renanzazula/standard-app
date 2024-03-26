package com.standard.security.listener;

import com.standard.entity.security.LoginSuccessEntity;
import com.standard.entity.security.UserEntity;
import com.standard.repository.security.LoginSuccessRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AuthenticationSuccessListener {

    private final LoginSuccessRepository loginSuccessRepository;

    @EventListener
    public void listen(AuthenticationSuccessEvent event) {

        if (event.getSource() instanceof UsernamePasswordAuthenticationToken token) {
            LoginSuccessEntity.LoginSuccessEntityBuilder build = LoginSuccessEntity.builder();

            token = (UsernamePasswordAuthenticationToken) event.getSource();
            if (token.getPrincipal() instanceof UserEntity user) {
                user = (UserEntity) token.getPrincipal();
                build.userId(user.getId()).username(user.getUsername());
                log.debug("User name logged in: " + user.getUsername());
            }

            if (token.getDetails() instanceof WebAuthenticationDetails details) {
                details = (WebAuthenticationDetails) token.getDetails();
                build.sourceIp(details.getRemoteAddress());
                log.debug("Source IP: " + details.getRemoteAddress());
            }

            LoginSuccessEntity loginSuccess = loginSuccessRepository.save(build.build());
            log.debug("login success saved id: " + loginSuccess.getId());
        }
    }
}
