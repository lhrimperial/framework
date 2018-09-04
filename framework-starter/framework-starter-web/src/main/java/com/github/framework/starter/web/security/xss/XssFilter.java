package com.github.framework.starter.web.security.xss;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * xss防御过滤器
 */
public class XssFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new XssRequestWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {
    }
}
