package com.github.framework.starter.mybatis;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.page.PageAutoDialect;
import com.github.pagehelper.page.PageParams;
import com.github.pagehelper.util.StringUtil;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Properties;

/**
 * 分页工具（设置查询上限）
 **/
public class FramePageHelper  extends PageHelper {
    private PageParams pageParams;
    private PageAutoDialect autoDialect;
    private int upperLimit;

    @Override
    public boolean skip(MappedStatement ms, Object parameterObject, RowBounds rowBounds) {
        if (!super.skip(ms, parameterObject, rowBounds)) {
            return false;
        }
        // 设置查询上限
        Page page = startPage(0, upperLimit, false);
        // ----------------以下拷贝自父类-----------------
        //设置默认的 count 列
        if (StringUtil.isEmpty(page.getCountColumn())) {
            page.setCountColumn(pageParams.getCountColumn());
        }
        autoDialect.initDelegateDialect(ms);
        return false;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);

        Field pageParamsField = ReflectionUtils.findField(PageHelper.class, "pageParams");
        ReflectionUtils.makeAccessible(pageParamsField);
        pageParams = (PageParams) ReflectionUtils.getField(pageParamsField, this);

        Field autoDialectField = ReflectionUtils.findField(PageHelper.class, "autoDialect");
        ReflectionUtils.makeAccessible(autoDialectField);
        autoDialect = (PageAutoDialect) ReflectionUtils.getField(autoDialectField, this);

        upperLimit = Integer.parseInt(properties.getProperty("upperLimit"));
    }
}
