package com.example.dome.common.entity.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Time 2019/8/27 16:42
 * @Author GuiPing.Xu
 * @Version 1.0.0
 * @Explain 自定义配置提示配置类
 */
@ConfigurationProperties(prefix = "biz")
@Data
public class MultiDataSources {
    private dataSource datasource;
    @Data
    static class dataSource{
        private String url;
        private String dateUserName;
        private String dataPassWord;
    }
}
