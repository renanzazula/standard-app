package com.standard.security.listener;

import com.standard.entity.security.LoginFailureEntity;
import com.standard.entity.security.LoginSuccessEntity;
import com.standard.entity.security.UserEntity;
import com.standard.repository.security.LoginFailureRepository;
import com.standard.repository.security.LoginSuccessRepository;
import com.standard.repository.security.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationEvents {

	private final LoginSuccessRepository loginSuccessRepository;
	private final LoginFailureRepository loginFailureRepository;
	private final UserRepository userRepository;

	@EventListener
	public void onSuccess(AuthenticationSuccessEvent event) {
		if (event.getSource() instanceof UsernamePasswordAuthenticationToken token) {
			LoginSuccessEntity.LoginSuccessEntityBuilder build = LoginSuccessEntity.builder();

			token = (UsernamePasswordAuthenticationToken) event.getSource();
			if (token.getPrincipal() instanceof UserEntity user) {
				user = (UserEntity) token.getPrincipal();
				build.user(user);
				log.debug("User name logged in: {}", user.getUsername());
			}

			if (token.getDetails() instanceof WebAuthenticationDetails details) {
				details = (WebAuthenticationDetails) token.getDetails();
				build.sourceIp(details.getRemoteAddress());
				log.debug("Source IP: {}", details.getRemoteAddress());
			}

			LoginSuccessEntity loginSuccess = loginSuccessRepository.save(build.build());
			log.debug("login success saved id: {}", loginSuccess.getId());
		}
	}



	@EventListener
	public void onFailure(AuthenticationFailureBadCredentialsEvent event) {
		log.debug("Login failure ");

		if (event.getSource() instanceof UsernamePasswordAuthenticationToken token) {
			token = (UsernamePasswordAuthenticationToken) event.getSource();
			LoginFailureEntity.LoginFailureEntityBuilder builder = LoginFailureEntity.builder();

			if (token.getPrincipal() instanceof String userName) {
				log.debug("Attempted Username: {}", token.getPrincipal());
				builder.username(userName);
				userRepository.findByUsername(userName).ifPresent(builder::user);
			}

			if (token.getDetails() instanceof WebAuthenticationDetails details) {
				details = (WebAuthenticationDetails) token.getDetails();
				log.debug("Source IP: {}", details.getRemoteAddress());
				builder.sourceIp(details.getRemoteAddress());
			}
			LoginFailureEntity failure = loginFailureRepository.save(builder.build());
			log.debug("Failure Event: {}", failure.getId());

			if (failure.getUser() != null) {
				lockUserAccount(failure.getUser());
			}
		}
	}

	private void lockUserAccount(UserEntity user) {
		List<LoginFailureEntity> failures = loginFailureRepository.findAllByUserIdAndCreatedDateAfter(user.getId(),
				Timestamp.valueOf(LocalDateTime.now().minusDays(1)));

		if(failures.size() > 3){
			log.debug("locking user account...: {}", user.getUsername());
			user.setAccountNonLocked(false);
			userRepository.save(user);
		}
	}
}