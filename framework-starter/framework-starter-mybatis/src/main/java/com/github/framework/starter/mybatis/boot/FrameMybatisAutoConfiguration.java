package com.github.framework.starter.mybatis.boot;

import com.github.framework.starter.core.ApplicationContexts;
import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import com.github.pagehelper.autoconfigure.PageHelperProperties;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

/**
 * mybatis自动配置类
 */
@Configuration
@AutoConfigureBefore(PageHelperAutoConfiguration.class)
@EnableConfigurationProperties(PageHelperProperties.class)
public class FrameMybatisAutoConfiguration {
    // 配置属性
    private static FrameMybatisProperties properties = ApplicationContexts.buildProperties(FrameMybatisProperties.class);
    @Autowired
    private PageHelperProperties pageHelperProperties;

    @PostConstruct
    public void init() {
        // 定制PageHelper
        if (StringUtils.isEmpty(pageHelperProperties.getSupportMethodsArguments())) {
            pageHelperProperties.setSupportMethodsArguments("true");
        }
        if (StringUtils.isEmpty(pageHelperProperties.getReasonable())) {
            pageHelperProperties.setReasonable("true");
        }
        pageHelperProperties.setDialect("com.github.framework.starter.mybatis.FramePageHelper");
        pageHelperProperties.getProperties().setProperty("upperLimit", Integer.toString(properties.getUpperLimit()));
    }

    // 配置扫描mapper接口
    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        configurer.setBasePackage(StringUtils.collectionToCommaDelimitedString(properties.getScan()));
        return configurer;
    }
}
