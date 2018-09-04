package com.github.framework.starter.jdbc.boot;

import com.github.framework.starter.jdbc.DataSource;
import com.github.framework.starter.jdbc.DataSourceHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 选择数据源注解aop
 */
@Component
@Aspect
public class DataSourceAspect {

    @Before("@annotation(dataSource)")
    public void before(DataSource dataSource) {
        DataSourceHolder.chooseDataSource(dataSource.value());
    }

    @After("@annotation(dataSource)")
    public void after(DataSource dataSource) {
        DataSourceHolder.chooseDefaultDataSource();
    }
}
