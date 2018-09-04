package com.github.framework.starter.jdbc.boot;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * 注解@DataSource自动配置类
 */
@Configuration
@EnableAspectJAutoProxy
@Import(DataSourceAspect.class)
public class DataSourceAspectAutoConfiguration {
}
