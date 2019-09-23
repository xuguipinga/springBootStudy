package com.example.dome.common.mutidatesource;

/**
 * @Time 2019/8/27 18:45
 * @Author GuiPing.Xu
 * @Version 1.0.0
 * @Explain dataSource的上下文 用来存储当前线程的数据源类型
 */
public class DataSourceContextHolder {
    private static final  ThreadLocal<String> contextHolder=new ThreadLocal<String>();

    /**
     * @param dataSourceType 数据库类型
     * @Description: 设置数据源类型
     */
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    /**
     * @Description: 获取数据源类型
     */
    public static String getDataSourceType() {
        return contextHolder.get();
    }

    /**
     * @Description: 清除数据源类型
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }

}
