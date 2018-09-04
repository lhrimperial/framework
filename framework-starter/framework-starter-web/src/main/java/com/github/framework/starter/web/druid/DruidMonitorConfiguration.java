package com.github.framework.starter.web.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author longhairen
 * @create 2018-02-25 19:17
 * @description
 **/
@Configuration
@ConditionalOnClass(DruidDataSource.class)
public class DruidMonitorConfiguration {

    @Bean
    public ServletRegistrationBean driudStatViewServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet());
        registrationBean.addUrlMappings("/druid/*");
        return registrationBean;
    }
}
