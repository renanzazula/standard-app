package com.standard.security;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RestHeaderAuthFilter {
        
//        extends AbstractAuthenticationProcessingFilter {
//
//    public RestHeaderAuthFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
//        super(requiresAuthenticationRequestMatcher);
//    }
//
//    @Override
//    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
//            throws IOException, ServletException {
//
//        HttpServletRequest request = (HttpServletRequest) req;
//        HttpServletResponse response = (HttpServletResponse) res;
//
//
//        if (logger.isDebugEnabled()) {
//            logger.debug("Request is to process authentication");
//        }
//
//        try {
//            Authentication authResult = attemptAuthentication(request, response);
//
//            if (authResult != null) {
//                successfulAuthentication(request, response, chain, authResult);
//            } else {
//                chain.doFilter(request, response);
//            }
//        } catch (AuthenticationException e) {
//            log.error("Authentication Failed", e);
//            unsuccessfulAuthentication(request, response, e);
//        }
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//        String userName = getUsername(request);
//        String password = getPassword(request);
//
//        if (userName == null) {
//            userName = "";
//        }
//
//        if (password == null) {
//            password = "";
//        }
//
//        log.debug("Authenticating User: " + userName);
//
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userName, password);
//
//        if (!StringUtils.isEmpty(userName)) {
//            return this.getAuthenticationManager().authenticate(token);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
//            Authentication authResult) {
//        if (logger.isDebugEnabled()) {
//            logger.debug("Authentication success. Updating SecurityContextHolder to contain: "  + authResult);
//        }
//        SecurityContextHolder.getContext().setAuthentication(authResult);
//    }
//
//    protected void unsuccessfulAuthentication(HttpServletResponse response) throws IOException {
//
//        SecurityContextHolder.clearContext();
//
//        if (log.isDebugEnabled()) {
//            log.debug("Authentication request failed");
//            log.debug("Updated SecurityContextHolder to contain null Authentication");
//        }
//
//        response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
//    }
//    
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request,
//            HttpServletResponse response, AuthenticationException failed)
//            throws IOException {
//
//        SecurityContextHolder.clearContext();
//
//        if (log.isDebugEnabled()) {
//            log.debug("Authentication request failed: " + failed.toString(), failed);
//            log.debug("Updated SecurityContextHolder to contain null Authentication");
//        }
//
//        response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
//    }
//    
//    private String getPassword(HttpServletRequest request) {
//        return request.getHeader("Api-Secret");
//    }
//
//    private String getUsername(HttpServletRequest request) {
//        return request.getHeader("Api-Key");
//    }
}
