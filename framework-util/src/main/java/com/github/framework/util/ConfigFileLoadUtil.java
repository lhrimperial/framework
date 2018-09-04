package com.github.framework.util;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author 龙海仁
 * @create：2016年1月6日 下午9:34:24
 * @description：
 */
public class ConfigFileLoadUtil {
	/**
     * 
     * <p>通过模块名称和文件名称查找文件</p> 
     * @param moduleName
     * @param fileName
     * @return
     * @throws IOException
     * @see
     */
	public static Resource[] getResourcesForClasspath(String moduleName, String fileName) throws IOException {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath*:com/**/" + moduleName
				+ "/**/"+fileName);
		return resources;
	}
	/**
	 * 
	 * <p>通过模块名称和文件名称查找文件</p> 
	 * @param moduleName
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @see
	 */
	public static InputStream getInputStreamForClasspath(String moduleName, String fileName) throws FileNotFoundException,IOException {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources("classpath*:com/**/" + moduleName
				+ "/**/"+fileName);
		if (resources == null || resources.length<1) {
			throw new FileNotFoundException("file '"+fileName+"' not found in this module");
		}
		InputStream in = resources[0].getInputStream();
		return in;
	}
	/**
	 * 
	 * <p>通过模块名称和文件名称查找文件</p> 
	 * @param moduleName
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @see
	 */
	public static Properties getPropertiesForClasspath(String moduleName, String fileName) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(getInputStreamForClasspath(moduleName,fileName));
		return properties;
	}

	public static InputStream getInputStreamForClasspath(String fileName) throws FileNotFoundException,IOException {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver
		.getResources("classpath*:" + fileName);
		if (resources == null || resources.length<1) {
			throw new FileNotFoundException("file '"+fileName+"' not found in this root path!");
		}
		InputStream in = resources[0].getInputStream();
		return in;
	}
	
	public static Properties getPropertiesForClasspath(String fileName) throws FileNotFoundException,IOException {
		Properties properties = new Properties();
		properties.load(getInputStreamForClasspath(fileName));
		return properties;
	}
}
