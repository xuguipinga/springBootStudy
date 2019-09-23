package com.example.dome.common.mutidatesource;


/**
 * @Time 2019/8/27 18:48
 * @Author GuiPing.Xu
 * @Version 1.0.0
 * @Explain 动态数据源
 */
public class AbstractRoutingDataSource extends org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }
}
