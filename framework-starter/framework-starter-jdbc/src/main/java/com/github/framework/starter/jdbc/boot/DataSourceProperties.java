package com.github.framework.starter.jdbc.boot;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 数据源配置属性
 */
@ConfigurationProperties("framework")
public class DataSourceProperties {
    /**
     * 默认的数据源
     */
    @NotNull
    @Valid
    private DetailProperties datasource;
    /**
     * 多数据源
     */
    @Valid
    private List<DetailProperties> datasources;

    /**
     * 是否打印SQL日志
     */
    private Boolean showSql;

    public DetailProperties getDatasource() {
        return datasource;
    }

    public void setDatasource(DetailProperties datasource) {
        this.datasource = datasource;
    }

    public List<DetailProperties> getDatasources() {
        return datasources;
    }

    public void setDatasources(List<DetailProperties> datasources) {
        this.datasources = datasources;
    }

    public Boolean getShowSql() {
        return showSql;
    }

    public void setShowSql(Boolean showSql) {
        this.showSql = showSql;
    }

    /**
     * 校验
     */
    public void check() {
        if (CollectionUtils.isEmpty(datasources)) {
            return;
        }
        // 多数据源情况
        Set<String> datasourceNames = new HashSet<>();

        List<DetailProperties> propertiesList = new ArrayList<>();
        propertiesList.add(datasource);
        propertiesList.addAll(datasources);
        for (DetailProperties properties : propertiesList) {
            if (StringUtils.isBlank(properties.datasourceName)) {
                throw new IllegalArgumentException("存在多个数据源，则每个数据源都必须指定datasourceName属性");
            }
            if (datasourceNames.contains(properties.datasourceName)) {
                throw new IllegalArgumentException("每个数据源的datasourceName属性必须唯一");
            }
            datasourceNames.add(properties.datasourceName);
        }
    }

    /**
     * 数据源详细配置属性
     */
    public static class DetailProperties {
        /**
         * 数据源名称（只有一个数据源时选填，存在多数据源时必填）
         */
        private String datasourceName;
        /**
         * 必填：数据库连接
         */
        @NotBlank
        private String url;
        /**
         * 必填：数据库用户名
         */
        @NotBlank
        private String username;
        /**
         * 必填：数据库密码
         */
        @NotBlank
        private String password;
        /**
         * 选填：初始数据库连接大小
         */
        private int initialSize = 10;
        /**
         * 选填：数据库最小闲置连接数
         */
        private int minIdle = 20;
        /**
         * 选填：数据库最大连接数
         */
        private int maxActive = 100;

        public String getDatasourceName() {
            return datasourceName;
        }

        public void setDatasourceName(String datasourceName) {
            this.datasourceName = datasourceName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getInitialSize() {
            return initialSize;
        }

        public void setInitialSize(int initialSize) {
            this.initialSize = initialSize;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public int getMaxActive() {
            return maxActive;
        }

        public void setMaxActive(int maxActive) {
            this.maxActive = maxActive;
        }
    }
}
