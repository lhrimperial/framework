package com.github.framework.starter.web.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

/**
 * 安全防御配置属性
 */
@ConfigurationProperties(SecurityProperties.PREFIX)
public class SecurityProperties {
    /**
     * 前缀
     */
    public static final String PREFIX = "framework.security";

    /**
     * 选填：需进行csrf防御的url（存在多个以","分开）
     */
    private Set<String> csrfUrls;

    public Set<String> getCsrfUrls() {
        return csrfUrls;
    }

    public void setCsrfUrls(Set<String> csrfUrls) {
        this.csrfUrls = csrfUrls;
    }
}
