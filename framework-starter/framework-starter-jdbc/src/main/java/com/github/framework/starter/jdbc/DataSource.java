package com.github.framework.starter.jdbc;

import java.lang.annotation.*;

/**
 * 选择数据源
 */
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    /**
     * 数据源名称
     */
    String value();
}
