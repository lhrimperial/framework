package com.github.framework.starter.web.security.csrf;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * csrf防御过滤器
 */
public class CsrfFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (shouldIgnore(request.getRequestURI())) {
            chain.doFilter(req, resp);
            return;
        }
        // 获取服务端token
        String serverToken = (String) request.getSession().getAttribute(CsrfUtils.CSRF_TOKEN_PARAMETER_NAME);
        if (StringUtils.isEmpty(serverToken)) {
            // 创建token
            serverToken = UUID.randomUUID().toString();
            request.getSession().setAttribute(CsrfUtils.CSRF_TOKEN_PARAMETER_NAME, serverToken);
        } else {
            // 获取浏览器token
            String clientToken = request.getHeader(CsrfUtils.CSRF_TOKEN_PARAMETER_NAME);
            if (StringUtils.isEmpty(clientToken)) {
                clientToken = request.getParameter(CsrfUtils.CSRF_TOKEN_PARAMETER_NAME);
            }
            if (!StringUtils.equals(clientToken, serverToken)) {
                // 如果浏览器token不正确
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "CSRF_TOKEN is not match");
                return;
            }
        }

        CsrfUtils.setCsrfToken(serverToken);
        chain.doFilter(req, resp);
        CsrfUtils.setCsrfToken(null);
    }

    @Override
    public void destroy() {
    }

    // 指定是否忽略进行csrf防御校验
    private boolean shouldIgnore(String url) {
        return url.endsWith(".css") || url.endsWith(".js");
    }
}
