package com.github.framework.starter.disconf.boot;

import com.githup.platform.config.client.ConfigProperties;
import org.springframework.core.env.EnumerablePropertySource;

import java.util.Set;

/**
 *
 */
public class DisconfPropertySource extends EnumerablePropertySource {

    public static final String DISCONF_PROPERTY_SOURCE_NAME = "disconf";

    protected DisconfPropertySource() {
        super(DISCONF_PROPERTY_SOURCE_NAME);
    }

    @Override
    public boolean containsProperty(String name) {
        return ConfigProperties.getProperties().containsKey(name);
    }

    @Override
    public String[] getPropertyNames() {
        Set<String> propertyNames = ConfigProperties.getProperties().stringPropertyNames();
        return propertyNames.toArray(new String[propertyNames.size()]);
    }

    @Override
    public Object getProperty(String name) {
        return ConfigProperties.getPropertiesByKey(name);
    }
}
