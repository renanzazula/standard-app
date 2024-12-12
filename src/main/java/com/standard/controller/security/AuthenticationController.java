package com.standard.controller.security;

import com.standard.domain.security.Login;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "Authentication", tags = "authentication")
@Slf4j
@RestController
@RequestMapping(AuthenticationController.AUTHENTICATION)
@RequiredArgsConstructor
public class AuthenticationController {

    public static final String AUTHENTICATION = "/public/api/v1/authentication";

    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private final AuthenticationManager authenticationManager;
    private final SessionAuthenticationStrategy sessionAuthenticationStrategy;

    @PostMapping("/login")
    public ResponseEntity<Login> login (@RequestBody Login authentication){
        Authentication authResp = authenticate(new UsernamePasswordAuthenticationToken(authentication.getUserId(), authentication.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authResp);
        authentication.setPassword(null);
        return new ResponseEntity<>(authentication, HttpStatus.OK);
    }

    @GetMapping("/logout")
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    private Authentication authenticate(UsernamePasswordAuthenticationToken authRequest) {
        Authentication authResp = null;
        try {
            log.info("Authenticating...");
            authResp = authenticationManager.authenticate(authRequest);
            log.info("Applying session fixation...");
            sessionAuthenticationStrategy.onAuthentication(authResp, request, response);
            log.info("after session Authentication Strategy - onAuthentication...");
        } catch (Exception exception) {
            SecurityContextHolder.clearContext();
            log.error("Credentials validation failed", exception);
            log.info("User credentials failed - user ID {}", authRequest.getPrincipal());
            throw exception;
        }
        return authResp;
    }
}
