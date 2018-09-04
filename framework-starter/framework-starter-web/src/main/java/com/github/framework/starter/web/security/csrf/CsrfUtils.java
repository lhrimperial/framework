package com.github.framework.starter.web.security.csrf;

/**
 * csrf工具类
 */
public class CsrfUtils {
    /**
     * csrf防御token的参数名
     */
    public static final String CSRF_TOKEN_PARAMETER_NAME = "CSRF_TOKEN";
    // token持有器
    private static final ThreadLocal<String> CSRF_TOKEN_HOLDER = new ThreadLocal<>();

    /**
     * 获取csrf防御Token
     */
    public static String getCsrfToken() {
        return CSRF_TOKEN_HOLDER.get();
    }

    // 设置csrf防御Token
    static void setCsrfToken(String csrfToken) {
        CSRF_TOKEN_HOLDER.set(csrfToken);
    }
}
