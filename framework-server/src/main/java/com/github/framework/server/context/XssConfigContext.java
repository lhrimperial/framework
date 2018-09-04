package com.github.framework.server.context;



import com.github.framework.util.string.StringUtils;

import java.util.Map;

/**
 * xss配置参数
 * 
 *
 */
public class XssConfigContext {
	
	/**
	 * 策略：替换成空串
	 */
	public static final String REPLACE_EMPTY = "REPLACE_EMPTY";
	/**
	 * 策略：替换成转义字符
	 */
	public static final String REPLACE_ESCAPE = "REPLACE_ESCAPE";
	/**
	 * 策略：重定向
	 */
	public static final String REDIRECT = "REDIRECT";
	/**
	 * 默认表达式
	 */
	public static final String DEFAULT_EXPRESSION = "(?i)(script)+";
	/**
	 * 表达式属性名
	 */
	public static final String EXPRESSION = "expression";
	/**
	 * 策略属性名
	 */
	public static final String TACTICS = "tactics";
	
	/**
	 * 路径属性名
	 */
	public static final String PATH = "path";
	
	Map<String, Object> context;

	public XssConfigContext(Map<String, Object> context) {
		this.context = context;
	}
	
	public String getExpression() {
		String expression = (String) context.get(EXPRESSION); 
		if(StringUtils.isBlank(expression)) {
			return DEFAULT_EXPRESSION;
		}
		return expression;
	}

	public String getTactics() {
		String tactics = (String) context.get(TACTICS);
		if(StringUtils.isBlank(tactics)) {
			return REPLACE_EMPTY;
		}
		return tactics;
	}
	
	public String getPath() {
		String path = (String) context.get(PATH);
		return path;
	}

}
