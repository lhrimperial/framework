package com.github.framework.starter.mybatis.boot;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Set;

/**
 * mybatis配置属性
 */
@ConfigurationProperties(prefix = FrameMybatisProperties.PREFIX)
public class FrameMybatisProperties {
    /**
     * 前缀
     */
    public static final String PREFIX = "mybatis";

    /**
     * 选填：扫描mapper接口的基础包路径（存在多个路径的话以","分隔）
     */
    @NotEmpty
    private Set<String> scan /*= new HashSet<String>() {{
        for (String basePakage : ApplicationContexts.getBasePackages()) {
            add(basePakage);
        }
    }}*/;
    /**
     * 选填：查询数量上限
     */
    private int upperLimit = 5000;

    public Set<String> getScan() {
        return scan;
    }

    public void setScan(Set<String> scan) {
        this.scan = scan;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }
}
