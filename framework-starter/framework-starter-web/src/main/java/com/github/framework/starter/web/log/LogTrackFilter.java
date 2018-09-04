package com.github.framework.starter.web.log;

import com.github.framework.starter.logging.support.LogTrackContext;

import javax.servlet.*;
import java.io.IOException;

/**
 *
 */
public class LogTrackFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LogTrackContext.initTrackToken();
        chain.doFilter(request, response);
        LogTrackContext.remove();
    }

    @Override
    public void destroy() {

    }
}
