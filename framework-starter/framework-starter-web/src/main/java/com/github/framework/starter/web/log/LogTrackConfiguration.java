package com.github.framework.starter.web.log;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class LogTrackConfiguration {

    @Bean
    public FilterRegistrationBean logTrackFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new LogTrackFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
