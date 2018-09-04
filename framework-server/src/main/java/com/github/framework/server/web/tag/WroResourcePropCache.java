package com.github.framework.server.web.tag;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


/**
* @ClassName: WroResourcePropCache
* @Description: 页面静态文件版本信息缓存
* @author 龙海仁
* @date 2016年4月22日 下午3:03:10
*
*/
public class WroResourcePropCache {
	
	private static final Log logger = LogFactory
	.getLog(WroResourcePropCache.class);

	private static final WroResourcePropCache INSTANCE = new WroResourcePropCache();
	
	private boolean devConfig = false;

	/**
	 * 保存所有缓存实例
	 */
	private final Map<String, Properties> caches = new ConcurrentHashMap<String, Properties>();

	/**
	 *  禁止从外部拿到实例
	  * 创建一个新的实例 WroResourcePropCache.
	  * @since 0.6
	 */
	private WroResourcePropCache() {
		Properties frameworkConfig = new Properties();
		try {
			InputStream  in = FileLoadUtil.getInputStreamForClasspath("framework.properties");
			PropertiesHelper propHelper = new PropertiesHelper(frameworkConfig);
			propHelper.load(in);
			devConfig = propHelper.getBoolean("dev", false);
		} catch (IOException e) {
			logger.debug(e.getMessage());
		}
	}
	
	public static boolean getDevConfig(){
		return WroResourcePropCache.getInstance().devConfig;
	}

	public static WroResourcePropCache getInstance() {
		return INSTANCE;
	}

	/**
	 * 根据wroResPropId获取缓存实例
	 * getWroResourceInfo
	 * @param moduleName
	 * @return Properties
	 * @since:
	 */
	public Properties getWroResourceInfo(String moduleName) {
		String wroResPropId = "wro_" + moduleName;
		Properties wroResPropInfo = caches.get(wroResPropId);
		if (WroResourcePropCache.getDevConfig() || wroResPropInfo == null) {
			wroResPropInfo = new Properties();
			try {
				InputStream  in = FileLoadUtil.getInputStreamForClasspath(moduleName, "wromapping.properties");
				wroResPropInfo.load(in);
				caches.put(wroResPropId, wroResPropInfo);
			} catch (IOException e) {
				logger.debug(e.getMessage());
			}
		}
		return wroResPropInfo;
	}
}