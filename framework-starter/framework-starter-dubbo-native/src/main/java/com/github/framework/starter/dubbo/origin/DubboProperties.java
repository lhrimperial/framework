package com.github.framework.starter.dubbo.origin;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * dubbo配置属性
 */
@Component
@Validated
public class DubboProperties {

    @Value("${dubbo.application}")
    @NotBlank
    private String application;

    @Value("${dubbo.owner}")
    private String owner;

    @Value("${dubbo.protocol.port}")
    @NotBlank
    private Integer protocolPort;

    @Value("${dubbo.registry.address}")
    @NotBlank
    private String registryAddress;

    @Value("${dubbo.provider.timeout}")
    private Integer providerTimeout;

    @Value("${dubbo.monitor.address}")
    private String monitorAddress;

    public String getApplication() {
        return application;
    }

    public String getOwner() {
        return owner;
    }

    public Integer getProtocolPort() {
        return protocolPort;
    }

    public String getRegistryAddress() {
        return registryAddress;
    }

    public Integer getProviderTimeout() {
        return providerTimeout;
    }

    public String getMonitorAddress() {
        return monitorAddress;
    }
}
