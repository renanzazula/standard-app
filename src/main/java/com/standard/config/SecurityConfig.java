package com.standard.config;

import com.standard.security.encoder.PasswordEncoderFactories;
import com.standard.security.filter.ConcurrentSessionFilter;
import com.standard.security.filter.RestHeaderAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final LogoutFilter logoutFilter;
    private final UserDetailsService userDetailsService;
    private final ConcurrentSessionFilter concurrencyFilter;
    private final HttpSessionCsrfTokenRepository csrfTokenRepository;
    private final PersistentTokenRepository persistentTokenRepository;
    private final CompositeSessionAuthenticationStrategy compositeSessionAuthenticationStrategy;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(concurrencyFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(logoutFilter, LogoutFilter.class);

        http.authorizeRequests(authorizes -> {
                    authorizes.antMatchers("/h2-console/**",
                            "/swagger-ui.html",
                            "/resources/**",
                            "/swagger-resources/**",
                            "/api/v1/authentication/login").permitAll();
                    authorizes.antMatchers("/api/**").authenticated();
                })
                .authorizeRequests().anyRequest().authenticated()
                .and().csrf().ignoringAntMatchers("/h2-console/**", "/api/**")
                .csrfTokenRepository(csrfTokenRepository)
                .and().rememberMe().tokenRepository(persistentTokenRepository).userDetailsService(userDetailsService)
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionAuthenticationStrategy(compositeSessionAuthenticationStrategy);

        http.headers().frameOptions().sameOrigin();
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }
    private RestHeaderAuthFilter headerAuthFilter(AuthenticationManager authenticationManager) {
        RestHeaderAuthFilter filter = new RestHeaderAuthFilter(new AntPathRequestMatcher("/private/**"));
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
