package com.standard.config;

/**
 * Created by jt on 6/13/20.
 */
public class SecurityConfig { 
        
        // extends WebSecurityConfigurerAdapter {
    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.addFilterBefore(headerAuthFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
//                .csrf().disable();
//
//        http.authorizeRequests(authorize -> {
//            authorize
//                    .antMatchers("/console/**").permitAll()
//                    .antMatchers("/public/**").permitAll()
//                    .antMatchers("/private/**").authenticated();
//        }).authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
//
//        // h2 console config 
//        http.headers().frameOptions().sameOrigin();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        
////        auth.inMemoryAuthentication().withUser("standard")
////                .password("{bcrypt10}$2a$10$Aype7wLEB5fMRUUEImlcnuCEtxhoAe2vmCmfbBUzs3qF3Qhwuyksm").roles("ADMIN");
//    }

//    private RestHeaderAuthFilter headerAuthFilter(AuthenticationManager authenticationManager) {
//        RestHeaderAuthFilter filter = new RestHeaderAuthFilter(new AntPathRequestMatcher("/private/**"));
//        filter.setAuthenticationManager(authenticationManager);
//        return filter;
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

}
