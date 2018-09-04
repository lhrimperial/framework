package com.github.framework.starter.dubbo.origin;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * dubbo自动配置类
 */
@Configuration
@Import({DubboProperties.class, DubboConfiguration.class})
public class DubboAutoConfiguration {
    // 自动导入相关配置类
}
