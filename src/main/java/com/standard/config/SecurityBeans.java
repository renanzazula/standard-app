package com.standard.config;

import com.standard.security.RegisterSessionAuthenticationStrategy;
import com.standard.security.TimeoutAuthenticationStrategy;
import com.standard.security.filter.ConcurrentSessionFilter;
import com.standard.security.handler.LogoutUnregisterHandler;
import com.standard.security.filter.RedirectSuccessFilter;
import com.standard.repository.security.UserSessionRepository;
import com.standard.service.configparam.ConfigParamService;
import com.standard.service.security.UserSessionService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Configuration
public class SecurityBeans {

    private final UserSessionService userSessionService;
    private final UserSessionRepository userSessionRepository;
    private final ConfigParamService configParamService;

    @Bean
    public AuthenticationEventPublisher authenticationEventPublisher(ApplicationEventPublisher applicationEventPublisher){
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Bean
    public HttpSessionCsrfTokenRepository csrfTokenRepository() {
        return new HttpSessionCsrfTokenRepository();
    }

    @Bean
    public LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter(redirectSuccessFilter(), logoutUnregisterHandler(), securityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl("/logout");
        return logoutFilter;
    }

    @Bean
    public RedirectSuccessFilter redirectSuccessFilter() {
        return new RedirectSuccessFilter();
    }

    @Bean
    public LogoutUnregisterHandler logoutUnregisterHandler() {
        return new LogoutUnregisterHandler(userSessionService);
    }

    @Bean
    public SecurityContextLogoutHandler securityContextLogoutHandler() {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.setInvalidateHttpSession(true);
        return logoutHandler;
    }

    @Bean
    public ConcurrentSessionFilter concurrencyFilter() {
        return new ConcurrentSessionFilter(userSessionRepository);
    }

    @Bean
    public CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy() {
        List<SessionAuthenticationStrategy> strategies = new ArrayList<>();
        strategies.add(new SessionFixationProtectionStrategy());
        strategies.add(new TimeoutAuthenticationStrategy(configParamService));
        strategies.add(new CsrfAuthenticationStrategy(csrfTokenRepository()));
        strategies.add(new RegisterSessionAuthenticationStrategy(userSessionRepository));
        return new CompositeSessionAuthenticationStrategy(strategies);
    }


}
