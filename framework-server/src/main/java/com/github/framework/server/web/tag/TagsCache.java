package com.github.framework.server.web.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
* @ClassName: TagsCache
* @Description: 页面国际化与权限配置缓存
* @author 龙海仁
* @date 2016年4月22日 下午3:02:53
*
*/
public class TagsCache {
	
	private static final Logger logger = LoggerFactory.getLogger(TagsCache.class);

	private static final String I18N_STRING = "i18n\\(\\s*['\"]([^'\"]+)['\"]";
	
	private static final String PERM_STRING = "isPermission\\(\\s*['\"]([^'\"]+)['\"]";
	
	private static final TagsCache INSTANCE = new TagsCache();

	/**
	 * 保存所有缓存实例
	 */
	private final Map<String,Map<String,String>> caches = new ConcurrentHashMap<String, Map<String,String>>();

	/**
	 *  禁止从外部拿到实例
	  * 创建一个新的实例 TagsCache.
	  * @since 0.6
	 */
	private TagsCache() {
	}

	public static TagsCache getInstance() {
		return INSTANCE;
	}
	
	/**
	 * 根据得到模块内所有JS的国际化与权限信息
	 * getTagesInfo
	 * @param moduleName
	 * @return Map<String,String>
	 * @since:
	 */
	public Map<String, String> getTagesInfo(String moduleName){
		StringBuilder keySb = new StringBuilder();
		StringBuilder urlSb = new StringBuilder();
		StringBuilder tagsIdBuffer = new StringBuilder("tags_");
		tagsIdBuffer.append(moduleName);
		String tagsId = tagsIdBuffer.toString();
		Map<String,String> tagsInfo = caches.get(tagsId);
		if (WroResourcePropCache.getDevConfig() || tagsInfo == null) {
			try {
				Resource[] resources = FileLoadUtil.getResourcesForServletpath("/scripts/" + moduleName + "/**/*.js");
	
				Set<String> keySet = new HashSet<String>();
				Set<String> urlSet = new HashSet<String>();
				if (resources != null && resources.length > 0) {
					Pattern i18nPattern = Pattern.compile(TagsCache.I18N_STRING);
					Pattern permPattern = Pattern.compile(TagsCache.PERM_STRING);
					
					for (Resource resource : resources) {
						BufferedReader br = null;
						try {
							br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
							char[] charBuffer = new char[1];
							StringBuilder fileBuffer = new StringBuilder();
							while ((br.read(charBuffer)) > 0) {
								fileBuffer.append(charBuffer);
							}
							String fileString = fileBuffer.toString();
							Matcher im = i18nPattern.matcher(fileString);
							while (im.find()) {
								keySet.add(im.group(1));
							}
							Matcher pm = permPattern.matcher(fileString);
							while (pm.find()) {
								urlSet.add(pm.group(1));
							}
						} catch (Exception e) {
							logger.error(e.getMessage());
						} finally {
							if (br != null) {
								br.close();
							}
						}
	
					}
					for (String str : keySet) {
						keySb.append(str);
						keySb.append(",");
					}
					for (String str : urlSet) {
						urlSb.append(str);
						urlSb.append(",");
					}
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			tagsInfo = new HashMap<String, String>();
			if (keySb.length() > 0) {
				tagsInfo.put("keys", keySb.substring(0, keySb.length() - 1));
			} else {
				tagsInfo.put("keys", "");
			}
			
			if (urlSb.length() > 0) {
				tagsInfo.put("urls", urlSb.substring(0, urlSb.length() - 1));
			} else {
				tagsInfo.put("urls", "");
			}
			caches.put(tagsId, tagsInfo);
		}
		return tagsInfo;
	}
	
	/**
	 * 通过缓存获取模块内groups中包含的所有JS的国际化及权限信息
	 * getTagesInfo
	 * @param moduleName
	 * @param groups
	 * @return Map<String,String>
	 * @since:
	 */
	public Map<String,String> getTagesInfo(String moduleName, String[] groups) {
		Set<String> keySet = new HashSet<String>();
		Set<String> urlSet = new HashSet<String>();
		StringBuilder keySb = new StringBuilder();
		StringBuilder urlSb = new StringBuilder();
		StringBuilder tagsIdBuffer = new StringBuilder("tags_");
		tagsIdBuffer.append(moduleName);
		for(String group : groups){
			tagsIdBuffer.append("_");
			tagsIdBuffer.append(group);
		}
		String tagsId = tagsIdBuffer.toString();
		Map<String,String> tagsInfo = caches.get(tagsId);
		if (WroResourcePropCache.getDevConfig() || tagsInfo == null) {
			for(String group : groups){
				BufferedReader br = null;
				try {
					Properties wroProperties = WroResourcePropCache.getInstance().getWroResourceInfo(moduleName);
					group = wroProperties.getProperty(group+".js",group+".js");
					InputStream in = FileLoadUtil.getInputStreamForServletpath("/scripts/" + moduleName + "/**/"+group);
					Pattern i18nPattern = Pattern.compile(TagsCache.I18N_STRING);
					Pattern permPattern = Pattern.compile(TagsCache.PERM_STRING);
					br = new BufferedReader(new InputStreamReader(in));
					char[] charBuffer = new char[1];
					StringBuilder fileBuffer = new StringBuilder();
					while ((br.read(charBuffer)) > 0) {
						fileBuffer.append(charBuffer);
					}
					String fileString = fileBuffer.toString();
					Matcher im = i18nPattern.matcher(fileString);
					while (im.find()) {
						keySet.add(im.group(1));
					}
					Matcher pm = permPattern.matcher(fileString);
					while (pm.find()) {
						urlSet.add(pm.group(1));
					}
				}catch (FileNotFoundException e) {
					logger.error("wro mapping '"+group+".js' not found in this module");
				}catch (IOException e) {
					logger.error(e.getMessage());
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
							logger.error(e.getMessage());
						}
					}
				}	
			}
			for (String str : keySet) {
				keySb.append(str);
				keySb.append(",");
			}
			for (String str : urlSet) {
				urlSb.append(str);
				urlSb.append(",");
			}
			tagsInfo = new HashMap<String, String>();
			if (keySb.length() > 0) {
				tagsInfo.put("keys", keySb.substring(0, keySb.length() - 1));
			} else {
				tagsInfo.put("keys", "");
			}
			
			if (urlSb.length() > 0) {
				tagsInfo.put("urls", urlSb.substring(0, urlSb.length() - 1));
			} else {
				tagsInfo.put("urls", "");
			}
			caches.put(tagsId, tagsInfo);
			
		}
		return tagsInfo;
	}
}