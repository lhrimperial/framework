package com.github.framework.starter.dubbo.origin;

import com.alibaba.dubbo.config.*;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.github.framework.starter.core.ApplicationContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * dubbo配置类
 */
@Import(DubboProperties.class)
@Configuration
public class DubboConfiguration {

    @Autowired
    private DubboProperties dubboProperties;

    @Bean
    public static AnnotationBean annotationBean() {
        AnnotationBean annotationBean = new AnnotationBean();
        annotationBean.setPackage(StringUtils.arrayToCommaDelimitedString(ApplicationContexts.getBasePackages()));
        return annotationBean;
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(dubboProperties.getApplication());
        applicationConfig.setOwner(dubboProperties.getOwner());
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setProtocol("zookeeper");
        registryConfig.setAddress(dubboProperties.getRegistryAddress());
        return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(dubboProperties.getProtocolPort());
        protocolConfig.setThreads(200);
        return protocolConfig;
    }

    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setTimeout(dubboProperties.getProviderTimeout());
        providerConfig.setRetries(0);
        providerConfig.setDelay(-1);
        return providerConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        consumerConfig.setCheck(false);
        consumerConfig.setLazy(true);
        consumerConfig.setLoadbalance("roundrobin");
        return consumerConfig;
    }

    @Bean
    public MonitorConfig monitorConfig() {
        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol("dubbo");
        monitorConfig.setAddress(dubboProperties.getMonitorAddress());
        return monitorConfig;
    }

    // dubbo服务引用工厂
    @Bean
    public DubboReferenceFactory dubboReferenceFactory(ApplicationConfig applicationConfig, List<RegistryConfig> registryConfigs) {
        return new DubboReferenceFactory(applicationConfig, registryConfigs);
    }
}
