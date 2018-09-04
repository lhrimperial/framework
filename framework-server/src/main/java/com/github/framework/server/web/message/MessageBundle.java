package com.github.framework.server.web.message;

import com.github.framework.server.cache.CacheManager;
import com.github.framework.server.cache.ICache;
import com.github.framework.server.cache.message.AbstractDynamicMessageCache;
import com.github.framework.server.cache.message.MessageCache;
import com.github.framework.server.context.UserContext;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;


/**
* @ClassName: MessageBundle
* @Description: 国际化资源接口实现类
* @author 龙海仁
* @date 2016年4月22日 下午2:56:41
*
*/
public class MessageBundle implements IMessageBundle {

	/**
	 * 
	 * @param locale
	 * @param key
	 * @param args
	 * @return
	 * @since:0.6
	 */
	@Override
	public String getMessage(Locale locale, String key, Object... args) {
	    if(key == null) {
	    	return null;
	    }
		MessageCache cache = (MessageCache) CacheManager.getInstance().getCache(MessageCache.UUID);
		if(locale == null) {
		    //没有传入locale的用服务器系统默认的locale
            locale = Locale.getDefault();
        }
        Properties properties = cache.get(locale.toString());
        if (properties != null) {
          String value = properties.getProperty(key, key);
          if (!value.equals(key)) {
              // 格式化
              return (args == null || args.length == 0) ? value
                      : MessageFormat.format(value, args);
          }
        }

		//一下为老的国际化资源取值
//		String moduleName = RequestContext.getCurrentContext().getModuleName();
//		Properties properties = cache.get(moduleName + "_" + locale);
//		if (properties != null) {
//			String value = properties.getProperty(key, key);
//			if (!value.equals(key)) {
//				// 格式化
//				return (args == null || args.length == 0) ? value
//						: MessageFormat.format(value, args);
//			} else {
//				properties = null;
//			}
//		}
//
//		properties = cache.get(moduleName + "_" + locale.getLanguage());
//		if (properties != null) {
//			String value = properties.getProperty(key, key);
//			if (!value.equals(key)) {
//				// 格式化
//				return (args == null || args.length == 0) ? value
//						: MessageFormat.format(value, args);
//			} else {
//				properties = null;
//			}
//		}
//
//		properties = cache.get(moduleName);
//		if (properties != null) {
//			String value = properties.getProperty(key, key);
//			if (!value.equals(key)) {
//				// 格式化
//				return (args == null || args.length == 0) ? value
//						: MessageFormat.format(value, args);
//			} else {
//				properties = null;
//			}
//		}
//
//		// 当前模块下没有国际化信息则从框架扩展模块获取
//		properties = cache.get("frameworkimpl" + "_" + locale);
//		if (properties != null) {
//			String value = properties.getProperty(key, key);
//			if (!value.equals(key)) {
//				// 格式化
//				return (args == null || args.length == 0) ? value
//						: MessageFormat.format(value, args);
//			} else {
//				properties = null;
//			}
//		}
//
//		properties = cache.get("frameworkimpl" + "_" + locale.getLanguage());
//		if (properties != null) {
//			String value = properties.getProperty(key, key);
//			if (!value.equals(key)) {
//				// 格式化
//				return (args == null || args.length == 0) ? value
//						: MessageFormat.format(value, args);
//			} else {
//				properties = null;
//			}
//		}
//
//		properties = cache.get("frameworkimpl");
//		if (properties != null) {
//			String value = properties.getProperty(key, key);
//			if (!value.equals(key)) {
//				// 格式化
//				return (args == null || args.length == 0) ? value
//						: MessageFormat.format(value, args);
//			} else {
//				properties = null;
//			}
//		}

		// 没有国际化信息返回key
		return key;

	}

	/**
	 *
	 * @param key
	 * @param args
	 * @return
	 * @since:0.6
	 */
	@Override
	public String getMessage(String key, Object... args) {
		return getMessage(UserContext.getUserLocale(), key, args);
	}

	/**
	 *
	 *      Object[]) getDynamicMessage
	 * @param key
	 * @param args
	 * @return
	 * @since:0.6
	 */
	@Override
	public String getDynamicMessage(String key, Object... args) {
		return getDynamicMessage(UserContext.getUserLocale(), key, args);
	}

	/**
	 *
	 * @param locale
	 * @param key
	 * @param args
	 * @return
	 * @since:0.6
	 */
	@Override
	public String getDynamicMessage(Locale locale, String key, Object... args) {
		ICache<String, Map<String, String>> cache = CacheManager.getInstance()
				.getCache(AbstractDynamicMessageCache.UUID);
		Map<String, String> map = cache.get(locale.toString());
		if (map == null) {
			return key;
		}
		String message = map.get(key);
		if (message == null) {
			return key;
		}
		return (args == null || args.length == 0) ? message : MessageFormat
				.format(message, args);
	}

}
