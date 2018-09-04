package com.github.framework.starter.disconf.boot;

import com.github.framework.starter.core.ApplicationContexts;
import com.githup.platform.config.client.zookeeper.ZookeeperConfig;
import com.githup.platform.config.client.zookeeper.ZookeeperConstant;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.StringUtils;

import java.util.Properties;
import java.util.Set;

/**
 *
 */
public class DisconfApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment environment = event.getEnvironment();
        try {
            new ZookeeperConfig().getProperties(buildSweetInitProperties());
            environment.getPropertySources().addBefore(StandardEnvironment.SYSTEM_PROPERTIES_PROPERTY_SOURCE_NAME, new DisconfPropertySource());
        } catch (Exception e) {
            ExceptionUtils.rethrow(e);
        }
    }


    private Properties buildSweetInitProperties() {
        DisconfProperties disconfProperties = ApplicationContexts.buildProperties(DisconfProperties.class);

        Properties properties = new Properties();
        properties.setProperty(ZookeeperConstant.ADDRESS, StringUtils.collectionToCommaDelimitedString(disconfProperties.getAddress()));
        properties.setProperty(ZookeeperConstant.APPLICATION, ApplicationContexts.getAppName());
        properties.setProperty(ZookeeperConstant.ENVIRONMENT, ApplicationContexts.getProfile());
        properties.setProperty(ZookeeperConstant.WATCH_ENABLE, Boolean.TRUE.toString());

        return properties;
    }

    /**
     *
     */
    @ConfigurationProperties(DisconfProperties.PREFIX)
    public static class DisconfProperties {
        public static final String PREFIX = "disconf.config";

        /**
         *
         */
        @NotEmpty
        private Set<String> address;

        public Set<String> getAddress() {
            return address;
        }

        public void setAddress(Set<String> address) {
            this.address = address;
        }
    }
}
