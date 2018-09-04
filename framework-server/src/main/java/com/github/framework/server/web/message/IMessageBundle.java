package com.github.framework.server.web.message;

import java.util.Locale;


/**
* @ClassName: IMessageBundle
* @Description: 国际化资源接口
* @author 龙海仁
* @date 2016年4月22日 下午2:56:30
*
*/
public interface IMessageBundle {
	/**
	 * 根据键取得国际化资源，并格式化
	 * getMessage
	 * @param key 
	 * @param args 格式化参数 
	 * @return
	 * @return String
	 * @since:0.6
	 */
	String getMessage(String key, Object... args);
	
	/**
	 * 取得指定地区的国际化资源
	 * getMessage
	 * @param locale
	 * @param key
	 * @param args 格式化参数
	 * @return
	 * @return String
	 * @since:0.6
	 */
	String getMessage(Locale locale, String key, Object... args);
	
	/**
	 * 取得动态国际化资源并格式化
	 * getDynamicMessage
	 * @param key
	 * @param args 格式化参数
	 * @return
	 * @return String
	 * @since:0.6
	 */
	String getDynamicMessage(String key, Object... args);
	
	/**
	 * 取得指定地区的动态国际化资源
	 * getDynamicMessage
	 * @param locale
	 * @param key
	 * @param args 格式化参数
	 * @return
	 * @return String
	 * @since:0.6
	 */
	String getDynamicMessage(Locale locale, String key, Object... args);

}
