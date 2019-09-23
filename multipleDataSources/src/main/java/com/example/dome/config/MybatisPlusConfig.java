package com.example.dome.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.dome.common.mutidatesource.AbstractRoutingDataSource;
import com.example.dome.common.mutidatesource.DSEnum;
import com.example.dome.config.properties.DruidProperties;
import com.example.dome.config.properties.MutiDataSourceProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.HashMap;

/**
 * @Time 2019/8/28 15:33
 * @Author GuiPing.Xu
 * @Version 1.0.0
 * @Explain
 */
@Configuration
@EnableTransactionManagement(order = 2)
@MapperScan(basePackages = {"com.example.dome.common.mapper"})
public class MybatisPlusConfig {
    @Autowired
    DruidProperties druidProperties;
    @Autowired
    MutiDataSourceProperties mutiDataSourceProperties;

    /**
     * 核心数据源
     */
    private DruidDataSource coreDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 另一个数据源
     */
    private DruidDataSource bizDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        druidProperties.config(dataSource);
        mutiDataSourceProperties.config(dataSource);
        return dataSource;
    }

    /**
     * 单数据源连接池配置  通过@ConditionalOnProperty来控制Configuration是否生效
     */
    @Bean
    @ConditionalOnProperty(prefix = "example", name = "muti-datasource-open", havingValue = "false")
    public DruidDataSource singleDatasource() {
        return coreDataSource();
    }

    /**
     * 多数据源连接池配置
     */
    @Bean
    @ConditionalOnProperty(prefix = "example", name = "muti-datasource-open", havingValue = "true")
    public AbstractRoutingDataSource mutiDataSource() {

        DruidDataSource coreDataSource = coreDataSource();
        DruidDataSource bizDataSource = bizDataSource();
        try {
            coreDataSource.init();
            bizDataSource.init();
        } catch (SQLException sql) {
            sql.printStackTrace();
        }

        AbstractRoutingDataSource dynamicDataSource = new AbstractRoutingDataSource();
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put(DSEnum.DATA_SOURCE_CORE, coreDataSource);
        hashMap.put(DSEnum.DATA_SOURCE_BIZ, bizDataSource);
        dynamicDataSource.setTargetDataSources(hashMap);
        dynamicDataSource.setDefaultTargetDataSource(coreDataSource);
        return dynamicDataSource;
    }
}
