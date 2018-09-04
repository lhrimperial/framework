package com.github.framework.starter.jdbc.boot;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.framework.starter.core.ApplicationContexts;
import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import javax.sql.XADataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 数据源配置
 */
@Configuration
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
public class FrameDataSourceAutoConfiguration {
    private static final Logger logger = LoggerFactory.getLogger(FrameDataSourceAutoConfiguration.class);

    // 数据源配置属性
    private DataSourceProperties properties = ApplicationContexts.buildProperties(DataSourceProperties.class);
    // 创建的所有数据源
    private Set<DataSource> dataSources = new HashSet<>();

    @Bean(destroyMethod = "")
    public DataSource dataSource() {
        properties.check();
        DataSource defaultDataSource = buildDruidDataSource(properties.getDatasource());
        if (CollectionUtils.isEmpty(properties.getDatasources())) {
            return defaultDataSource;
        }
        // 多数据源情况
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(properties.getDatasource().getDatasourceName(), defaultDataSource);
        for (DataSourceProperties.DetailProperties detailProperties : properties.getDatasources()) {
            targetDataSources.put(detailProperties.getDatasourceName(), buildDruidDataSource(detailProperties));
        }

        MultiDataSource dataSource = new MultiDataSource();
        dataSource.setDefaultTargetDataSource(defaultDataSource);
        dataSource.setTargetDataSources(targetDataSources);

        return dataSource;
    }

    @PreDestroy
    public void destroy() {
        for (DataSource dataSource : dataSources) {
            try {
                dataSource.getConnection().close();
            } catch (Throwable e) {
                logger.error("关闭druid数据源出错：", e);
            }
        }
    }

    // 构建druid数据源
    private DataSource buildDruidDataSource(DataSourceProperties.DetailProperties detailProperties) {
        DatabaseDriver driver = DatabaseDriver.fromJdbcUrl(detailProperties.getUrl());

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driver.getDriverClassName());
        dataSource.setUrl(detailProperties.getUrl());
        dataSource.setUsername(detailProperties.getUsername());
        dataSource.setPassword(detailProperties.getPassword());
        dataSource.setInitialSize(detailProperties.getInitialSize());
        dataSource.setMinIdle(detailProperties.getMinIdle());
        dataSource.setMaxActive(detailProperties.getMaxActive());
        dataSource.setValidationQuery(driver.getValidationQuery());
        try {
            dataSource.setFilters("stat,wall");
            dataSource.init();
        } catch (SQLException e) {
            ExceptionUtils.rethrow(e);
        }
        if (!StringUtils.isEmpty(properties.getShowSql()) && properties.getShowSql()) {
            dataSources.add(new Log4jdbcProxyDataSource(dataSource));
        } else {
            dataSources.add(dataSource);
        }

        return dataSource;
    }
}
