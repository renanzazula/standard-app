package com.standard.config;

import com.standard.security.PasswordEncoderFactories;
import com.standard.security.RestHeaderAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by jt on 6/13/20.
 */
@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(headerAuthFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();

        http.authorizeRequests(authorize -> {
            authorize
                    .antMatchers("/console/**").permitAll()
                    .antMatchers("/public/**").permitAll()
                    .antMatchers("/private/**").authenticated();
        }).authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();

        // h2 console config 
        http.headers().frameOptions().sameOrigin();
    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        
////        auth.inMemoryAuthentication().withUser("standard")
////                .password("{bcrypt10}$2a$10$Aype7wLEB5fMRUUEImlcnuCEtxhoAe2vmCmfbBUzs3qF3Qhwuyksm").roles("ADMIN");
//    }

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
