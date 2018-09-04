package com.github.framework.server.web.security;

import com.github.framework.server.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限控制拦截器
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {
    private static final long serialVersionUID = -5665511978967345874L;
    private Logger logger = LoggerFactory.getLogger(SecurityInterceptor.class);

    /** 是否忽略检查权限表里没有映射的URL */
    private boolean ignoreUnstoredFunction = true;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        final String url = RequestContext.getCurrentContext().getRemoteRequestURL();
        logger.info("request url : " + url);
        if (!handlerMethod.hasMethodAnnotation(SecurityNonCheckRequired.class)) {
            SecurityAccessor.checkURLAccessSecurity(url, this.ignoreUnstoredFunction);
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    public boolean isIgnoreUnstoredFunction() {
        return ignoreUnstoredFunction;
    }

    public void setIgnoreUnstoredFunction(boolean ignoreUnstoredFunction) {
        this.ignoreUnstoredFunction = ignoreUnstoredFunction;
    }
}
