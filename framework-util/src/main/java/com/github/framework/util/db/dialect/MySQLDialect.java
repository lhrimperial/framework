package com.github.framework.util.db.dialect;


/**
 * @author longhairen
 * @create 2017/6/6 0006 下午 7:24
 * MySQL的分页方言
 */
public class MySQLDialect implements Dialect {

	private static final String LIMIT = " limit ";

	@Override
	public boolean supportsLimitOffset() {
		return true;
	}

	@Override
	public boolean supportsLimit() {
		return true;
	}

	@Override
	public String getLimitString(String sql, int offset, int limit) {
		return getLimitString(sql,offset, Integer.toString(offset),limit, Integer.toString(limit));
	}

	/**
	 * @param sql
	 * @param offset
	 * @param offsetPlaceholder
	 * @param limit
	 * @param limitPlaceholder
	 * @return
	 * @since:0.6
	 */
	public String getLimitString(String sql, int offset,
                                 String offsetPlaceholder, int limit, String limitPlaceholder) {
		if (offset > 0) {
			return sql + LIMIT + offsetPlaceholder + "," + limitPlaceholder;
		} else {
			return sql + LIMIT + limitPlaceholder;
		}
	}

	/**
	 * 为mysql 的语句添加limit限制
	 * getLimitString
	 * @param sql
	 * @param limit
	 * @return
	 * @since JDK1.6
	 */
	@Override
	public String getLimitString(String sql, int limit) {
		if(!checkLimit(sql)){
			return sql + LIMIT + limit;
		}
		return sql;
	}
	
	/**
	 * 判断sql语句是否已经加了limit限制
	 * checkLimit
	 * @param sql
	 * @return
	 * @return boolean
	 * @since JDK1.6
	 */
	private boolean checkLimit(String sql){
		boolean isLimited = false;
		if(sql.toLowerCase().indexOf(LIMIT)!=-1){
			isLimited = true;
		}
		return isLimited;
	}
}