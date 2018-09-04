package com.github.framework.util.db.helper;

import com.github.framework.util.db.dialect.Dialect;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;


/**
* @ClassName: OffsetLimitInterceptor
* @Description: 为ibatis3提供基于方言(Dialect)的分页查询的插件
 * 
 * 将拦截Executor.query()方法实现分页方言的插入.
 * 
 * 配置文件内容:
 * 
 * <pre>
 * 	&lt;plugins>
 * 	&lt;plugin interceptor="cn.org.rapid_framework.ibatis3.plugin.OffsetLimitInterceptor">
 * 		&lt;property name="dialectClass" value="cn.org.rapid_framework.jdbc.dialect.MySQLDialect"/>
 * 	&lt;/plugin>
 * &lt;/plugins>
 * </pre>
 * 
 *
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
    RowBounds.class, ResultHandler.class }) })
public class OffsetLimitInterceptor implements Interceptor {

    private static int mappedStatementIndex = 0;
    private static int parameterIndex = 1;
    private static int rowboundsIndex = 2;
    private static int resultHandlerIndex = 3;

    // 设置方言
    private Dialect dialect;

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

    public Object intercept(Invocation invocation) throws Throwable {
        processIntercept(invocation.getArgs());
        return invocation.proceed();
    }

    /**
     * 拦截分页请求，使用方言将原sql转化成分页sql processIntercept
     * 
     * @param queryArgs
     * @return void
     * @since:0.6
     */
    void processIntercept(final Object[] queryArgs) {
        // queryArgs = query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler)
        MappedStatement ms = (MappedStatement) queryArgs[mappedStatementIndex];
        Object parameter = queryArgs[parameterIndex];
        final RowBounds rowBounds = (RowBounds) queryArgs[rowboundsIndex];
        int offset = rowBounds.getOffset();
        int limit = rowBounds.getLimit();

        if (dialect.supportsLimit() && (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)) {
            BoundSql boundSql = ms.getBoundSql(parameter);
            String sql = boundSql.getSql().trim();
            if (dialect.supportsLimitOffset()) {
                sql = dialect.getLimitString(sql, offset, limit);
                offset = RowBounds.NO_ROW_OFFSET;
            } else {
                sql = dialect.getLimitString(sql, 0, limit);
            }
            limit = RowBounds.NO_ROW_LIMIT;

            queryArgs[rowboundsIndex] = new RowBounds(offset, limit);
            BoundSql newBoundSql =
                new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            // 将原有的BoundSql中的MetaParameter复制到新的BoundSql中
            copyMetaParameters(boundSql, newBoundSql);
            MappedStatement newMs = copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
            queryArgs[mappedStatementIndex] = newMs;
        }
    }
        
    /**
     * <p>复制BoundSql的MetaParameter</p> 
     * @param lhs
     * @param rhs
     */
    private void copyMetaParameters(BoundSql lhs, BoundSql rhs) {
        for (ParameterMapping map : lhs.getParameterMappings()) {
            String key = map.getProperty();
            Object value = lhs.getAdditionalParameter(key);
            if (null != value) {
                rhs.setAdditionalParameter(key, value);
            }
        }
    }

    /**
     * 
     * <p>获取MappedStatement</p> 
     * @param ms
     * @param newSqlSource
     * @return
     * @see
     */
    private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
        Builder builder =
            new Builder(ms.getConfiguration(), ms.getId(), newSqlSource, ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if(ms.getKeyProperties()!=null){
        	String[] keyProperties = ms.getKeyProperties();
        	StringBuffer keyPropertie = new StringBuffer();
        	for (int i = 0; i < keyProperties.length; i++) {
        		keyPropertie.append(keyProperties[i]);
        		if(i < keyProperties.length -1){
        			keyPropertie.append(",");
        		}
			}
        	builder.keyProperty(keyPropertie.toString());
        }

        // setStatementTimeout()
        builder.timeout(ms.getTimeout());

        // setStatementResultMap()
        builder.parameterMap(ms.getParameterMap());

        // setStatementResultMap()
        builder.resultMaps(ms.getResultMaps());
        builder.resultSetType(ms.getResultSetType());

        // setStatementCache()
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
    /**
     * 
     * <p>设置方言</p> 
     * @param properties 
     * @see Interceptor#setProperties(Properties)
     */
    public void setProperties(Properties properties) {
        String dialectClass = new PropertiesHelper(properties).getRequiredString("dialectClass");
        try {
            dialect = (Dialect) Class.forName(dialectClass).newInstance();
        } catch (Exception e) {
            throw new RuntimeException("cannot create dialect instance by dialectClass:" + dialectClass, e);
        }
    }
    /**
     * 
     * 设置分页boundsql
     * @since
     * @version
     */
    public static class BoundSqlSqlSource implements SqlSource {
        private BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

}
