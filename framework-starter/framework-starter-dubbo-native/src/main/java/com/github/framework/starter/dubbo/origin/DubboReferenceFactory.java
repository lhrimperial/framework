package com.github.framework.starter.dubbo.origin;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * dubbo服务引用工厂
 */
public class DubboReferenceFactory {
    /**
     * dubbo服务提供者超时时间
     */
    public static final int PROVIDER_TIME_OUT = -1;

    // 应用配置
    private ApplicationConfig applicationConfig;
    // dubbo服务注册配置
    private List<RegistryConfig> registryConfigs;
    // dubbo服务引用缓存
    private Map<CacheKey, ReferenceConfig> cache = new ConcurrentHashMap<>();

    public DubboReferenceFactory(ApplicationConfig applicationConfig, List<RegistryConfig> registryConfigs) {
        this.applicationConfig = applicationConfig;
        this.registryConfigs = registryConfigs;
    }

    /**
     * 获取dubbo服务引用（使用服务提供者超时时间）
     *
     * @param interfaceClass 服务接口类型
     * @param group          服务组名
     * @param version        服务版本
     * @param <T>            服务接口
     * @return dubbo服务引用
     */
    public <T> T getReference(Class<T> interfaceClass, String group, String version) {
        return getReference(interfaceClass, group, version, PROVIDER_TIME_OUT);
    }

    /**
     * 获取dubbo服务引用
     *
     * @param interfaceClass 服务接口类型
     * @param group          服务组名
     * @param version        服务版本
     * @param timeout        服务超时时间（单位：毫秒）
     * @param <T>            服务接口
     * @return dubbo服务引用
     */
    public <T> T getReference(Class<T> interfaceClass, String group, String version, int timeout) {
        CacheKey cacheKey = new CacheKey(interfaceClass, group, version, timeout);
        ReferenceConfig<T> referenceConfig = cache.get(cacheKey);
        if (referenceConfig == null) {
            synchronized (cache) {
                referenceConfig = cache.get(cacheKey);
                if (referenceConfig == null) {
                    referenceConfig = buildReferenceConfig(interfaceClass, group, version, timeout);
                    cache.put(cacheKey, referenceConfig);
                }
            }
        }
        return referenceConfig.get();
    }

    /**
     * 关闭
     */
    public void close() {
        for (ReferenceConfig referenceConfig : cache.values()) {
            referenceConfig.destroy();
        }
        cache.clear();
    }

    // 构建dubbo服务引用
    private <T> ReferenceConfig<T> buildReferenceConfig(Class<T> interfaceClass, String group, String version, int timeout) {
        ReferenceConfig<T> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistries(registryConfigs);
        referenceConfig.setInterface(interfaceClass);
        referenceConfig.setGroup(group);
        referenceConfig.setVersion(version);
        referenceConfig.setCheck(false);
        if (timeout != PROVIDER_TIME_OUT) {
            referenceConfig.setTimeout(timeout);
        }

        return referenceConfig;
    }

    // 缓存key
    private static class CacheKey {
        // 接口
        private Class interfaceClass;
        // 组名
        private String group;
        // 版本
        private String version;
        // 超时时间（毫秒）
        private int timeout;

        public CacheKey(Class interfaceClass, String group, String version, int timeout) {
            this.interfaceClass = interfaceClass;
            this.group = group;
            this.version = version;
            this.timeout = timeout;
        }

        @Override
        public int hashCode() {
            return Objects.hash(interfaceClass, group, version, timeout);
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof CacheKey)) {
                return false;
            }
            CacheKey other = (CacheKey) obj;
            return interfaceClass == other.interfaceClass
                    && StringUtils.equals(group, other.group)
                    && StringUtils.equals(version, other.version)
                    && timeout == other.timeout;
        }
    }
}
