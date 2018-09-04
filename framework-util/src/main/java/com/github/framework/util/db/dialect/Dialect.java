package com.github.framework.util.db.dialect;



/**
 * @author longhairen
 * @create 2017/6/6 0006 下午 7:24
 * 数据库方言接口
 */
public interface Dialect {
    /**
     * 是否支持分页
     * @return
     */
    boolean supportsLimit();

    /**
     * 是否支持分页
     * @return
     */
    boolean supportsLimitOffset();
    
    /**
     * 将sql变成分页sql语句,直接使用offset,limit的值作为占位符.</br>
     * 源代码为: getLimitString(sql,offset,String.valueOf(offset),limit,String.valueOf(limit))
     */
    String getLimitString(String sql, int offset, int limit) ;

    /**
     * 将sql变成分页sql语句,提供将offset及limit使用占位符(placeholder)替换.
     * <pre>
     * 如mysql
     * dialect.getLimitString("select * from user", 12, ":offset",0,":limit") 将返回
     * select * from user limit :offset,:limit
     * </pre>
     * @return 包含占位符的分页sql
     */
    String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder);

    /**
     *
     * @param sql
     * @param limit
     * @return
     */
    String getLimitString(String sql, int limit);
}