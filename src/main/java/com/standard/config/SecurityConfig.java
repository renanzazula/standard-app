package com.standard.config;

import com.standard.repository.security.UserSessionRepository;
import com.standard.security.RegisterSessionAuthenticationStrategy;
import com.standard.security.TimeoutAuthenticationStrategy;
import com.standard.security.filter.ConcurrentSessionFilter;
import com.standard.security.filter.CsrfTokenResponseFilter;
import com.standard.security.filter.RedirectSuccessFilter;
import com.standard.service.configparam.ConfigParamService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.csrf.CsrfAuthenticationStrategy;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ConfigParamService configParamService;
    private final UserSessionRepository userSessionRepository;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/public/**").permitAll()
                        .requestMatchers("/private/**").authenticated()
                        .anyRequest().authenticated())
                        .cors( cors -> corsConfigurationSource())
                        .csrf(csrf -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/public/**")))
                        .securityContext(securityContext -> securityContext.requireExplicitSave(false)) // Save SecurityContext automatically
                        .sessionManagement(session -> session.sessionAuthenticationStrategy(compositeSessionAuthenticationStrategy()))
                        .addFilterAfter(new CsrfTokenResponseFilter(), CsrfFilter.class);
        return http.build();
    }

     @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Bean
    public HttpSessionCsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
        csrfTokenRepository.setHeaderName("X-CSRF-TOKEN"); // Custom header name
        csrfTokenRepository.setParameterName("_csrf"); // Custom parameter name
        return csrfTokenRepository;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200");
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "X-CSRF-TOKEN"));
        configuration.setAllowCredentials(true);
        configuration.setExposedHeaders(List.of("Set-Cookie", "X-CSRF-TOKEN"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Bean
    public RedirectSuccessFilter redirectSuccessFilter() {
        return new RedirectSuccessFilter();
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
