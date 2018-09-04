package com.github.framework.starter.web.security;

import com.github.framework.starter.core.ApplicationContexts;
import com.github.framework.starter.web.security.csrf.CsrfFilter;
import com.github.framework.starter.web.security.xss.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.CollectionUtils;

/**
 * 安全防御配置
 */
@Configuration
public class SecurityConfiguration {
    /**
     * 基础优先级
     */
    public static final int BASE_ORDER = Ordered.LOWEST_PRECEDENCE - 100;
    // 配置属性
    private SecurityProperties properties = ApplicationContexts.buildProperties(SecurityProperties.class);

    @Bean
    public FilterRegistrationBean xssFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new XssFilter());
        registrationBean.setOrder(BASE_ORDER);
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean csrfFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new CsrfFilter());
        registrationBean.setOrder(BASE_ORDER + 1);
        if (!CollectionUtils.isEmpty(properties.getCsrfUrls())) {
            registrationBean.setUrlPatterns(properties.getCsrfUrls());
        } else {
            registrationBean.setEnabled(false);
        }
        return registrationBean;
    }
}
