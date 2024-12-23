package com.standard.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CsrfTokenResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException     {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        CsrfToken csrfToken = (CsrfToken) httpServletRequest.getAttribute(CsrfToken.class.getName());
        if (csrfToken != null) {
            httpServletResponse.setHeader("X-CSRF-TOKEN", csrfToken.getToken());
        }
        chain.doFilter(request, response);
    }
}
