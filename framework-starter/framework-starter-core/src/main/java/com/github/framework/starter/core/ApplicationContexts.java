package com.github.framework.starter.core;

import com.github.framework.util.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.MDC;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * 应用上下文
 **/
public class ApplicationContexts {

    // spring环境
    private static ConfigurableEnvironment environment;
    // spring容器
    private static ConfigurableApplicationContext applicationContext;
    // 应用信息
    private static AppInfo appInfo;

    public static void init(String appName) {
        appInfo = new AppInfo();
        appInfo.appName = appName;
        appInfo.logPath = "/opt/tomcat-" + appName + "/logs";
        //日志输出目录
        System.setProperty("logPath", appInfo.logPath);

        try {
            appInfo.hostName = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void setProfileIfNotExists(String profile) {
        if(StringUtils.isEmpty(PropertyUtils.getProperty("spring.profiles.active"))) {
            System.setProperty("spring.profiles.active", profile);
        }
    }

    public static String getAppName() {
        return appInfo == null?"":appInfo.appName;
    }

    public static String getHostName() {
        return appInfo == null?"":appInfo.hostName;
    }

    /**
     * 设置spring环境
     */
    public static void setEnvironment(ConfigurableEnvironment environment) {
        ApplicationContexts.environment = environment;
    }

    /**
     * 设置spring容器
     */
    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        ApplicationContexts.applicationContext = applicationContext;
    }

    /**
     * 获取Environment
     */
    public static Environment getEnvironment() {
        if (getApplicationContext() == null) {
            return environment;
        } else {
            return getApplicationContext().getEnvironment();
        }
    }

    /**
     * 获取ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取被自动扫描的包路径
     */
    public static String[] getBasePackages() {
        List<String> basePackages = AutoConfigurationPackages.get(getApplicationContext());
        return basePackages.toArray(new String[basePackages.size()]);
    }

    /**
     * 获取当前环境
     */
    public static String getProfile() {
        return getEnvironment().getActiveProfiles()[0];
    }

    /**
     * 根据environment构建属性对象
     *
     * @param targetClass 目标类型
     * @return 属性对象
     */
    public static <T> T buildProperties(Class<T> targetClass) {
        PropertiesBinder binder = new PropertiesBinder(((ConfigurableEnvironment) getEnvironment()).getPropertySources());
        return binder.build(targetClass);
    }

    // 应用信息
    private static class AppInfo {
        // 应用名
        private String appName;
        // http端口
        private int httpPort;
        // 配置目录路径
        private String configPath;
        // 数据目录路径
        private String dataPath;
        // 日志目录路径
        private String logPath;
        //本机的地址
        private String hostName;
    }
}
