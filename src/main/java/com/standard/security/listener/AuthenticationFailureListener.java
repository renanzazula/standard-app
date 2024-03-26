package com.standard.security.listener;

import com.standard.entity.security.LoginFailureEntity;
import com.standard.entity.security.UserEntity;
import com.standard.repository.security.LoginFailureRepository;
import com.standard.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationFailureListener {

    private final LoginFailureRepository loginFailureRepository;
    private final UserRepository userRepository;

    @EventListener
    public void failureListen(AuthenticationFailureBadCredentialsEvent event){
        log.debug("Login failure ");

        if (event.getSource() instanceof UsernamePasswordAuthenticationToken token) {
            token = (UsernamePasswordAuthenticationToken) event.getSource();
            LoginFailureEntity.LoginFailureEntityBuilder builder = LoginFailureEntity.builder();

            if (token.getPrincipal() instanceof String userName) {
                log.debug("Attempted Username: " + token.getPrincipal());
                builder.username(userName);
                userRepository.findByUsername((String) token.getPrincipal()).ifPresent(builder::user);
            }

            if (token.getDetails() instanceof WebAuthenticationDetails details) {
                details = (WebAuthenticationDetails) token.getDetails();
                log.debug("Source IP: " + details.getRemoteAddress());
                builder.sourceIp(details.getRemoteAddress());
            }
            LoginFailureEntity failure = loginFailureRepository.save(builder.build());
            log.debug("Failure Event: " + failure.getId());

            if (failure.getUser() != null) {
                lockUserAccount(failure.getUser());
            }
        }

    }

    private void lockUserAccount(UserEntity user) {
        List<LoginFailureEntity> failures = loginFailureRepository.findAllByUserIdAndCreatedDateAfter(user.getId(),
                Timestamp.valueOf(LocalDateTime.now().minusDays(1)));

        if(failures.size() > 3){
            log.debug("locking user account...: " + user.getUsername());
            user.setAccountNonLocked(false);
            userRepository.save(user);
        }
    }
}
