package com.github.framework.starter.mybatis.boot;

import com.github.framework.starter.core.ApplicationContexts;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.util.ObjectUtils;

/**
 * mapper的xml文件路径设置
 */
public class MapperLocationsApplicationListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    /**
     * 默认的mapper的xml文件路径
     */
    public static final String DEFAULT_MAPPER_LOCATION = "classpath*:/mybatis/**/*Mapper.xml";
    // mapper的xml文件路径配置属性名
    private static final String MAPPER_LOCATIONS_PROPERTY_NAME = "mybatis.mapper-locations";

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        MybatisProperties mybatisProperties = ApplicationContexts.buildProperties(MybatisProperties.class);
        if (ObjectUtils.isEmpty(mybatisProperties.getMapperLocations())) {
            System.setProperty(MAPPER_LOCATIONS_PROPERTY_NAME, DEFAULT_MAPPER_LOCATION);
        }
    }
}
