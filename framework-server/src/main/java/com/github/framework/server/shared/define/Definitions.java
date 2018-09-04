package com.github.framework.server.shared.define;


/**
* @ClassName: Definitions
* @Description: 框架定义类
* @author 龙海仁
* @date 2016年4月22日 下午3:06:03
*
*/
public final class Definitions {
	/**
	 * 用户id key定义
	 */
	public static final String KEY_USER = "FRAMEWORK__KEY_USER__";
	
	/**
	 * 国际化key 定义
	 */
	public static final String KEY_LOCALE = "FRAMEWORK__KEY_LOCALE__";
	
	/**
	 * 请求key定义
	 */
	public static final String KEY_REQUEST_URL = "FRAMEWORK_KEY_REQUEST_URL";
	// the client send request type | text/html;text/json;application-data/stream,etc
	public static final String KEY_REQUEST_TYPE ="FRAMEWORK_KEY_REQUEST_TYPE";
	/**
	 * 角色缓存key
	 */
	public static final String KEY_ROLE_CACHE = "FRAMEWORK_KEY_ROLE_CACHE";
	
	/**
	 * 功能缓存key
	 */
	public static final String KEY_FUNCTION_CACHE = "FRAMEWORK_KEY_FUNCTION_CACHE";
	
	/**
	 * 用户缓存key
	 */
	public static final String KEY_USER_CACHE = "FRAMEWORK_KEY_USER_CACHE";
	
	/**
	 * spring加载bean时遇到id重复 通过该标识判断 是覆盖 还是抛出异常
	 * true 允许覆盖
	 * false 抛出异常
	 */
	public static final String ALLOW_BEAN_DEFINITION_OVERRIDING = "allowBeanDefinitionOverriding";

	private Definitions() {
	}
	
}
