package com.github.framework.starter.jdbc;

/**
 * 数据源持有器
 */
public final class DataSourceHolder {
    // 当前数据源名称
    private static final ThreadLocal<String> CURRENT_DATASOURCE_NAME = new ThreadLocal<>();

    /**
     * 选择数据源
     *
     * @param datasourceName 数据源名称
     */
    public static void chooseDataSource(String datasourceName) {
        CURRENT_DATASOURCE_NAME.set(datasourceName);
    }

    /**
     * 选择默认数据源
     */
    public static void chooseDefaultDataSource() {
        CURRENT_DATASOURCE_NAME.remove();
    }

    /**
     * 获取当前数据源
     *
     * @return 当前数据源名称
     */
    public static String getCurrentDataSource() {
        return CURRENT_DATASOURCE_NAME.get();
    }
}
