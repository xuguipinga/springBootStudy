package com.example.dome;

import com.example.dome.common.entity.properties.MultiDataSources;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({MultiDataSources.class})
public class DomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DomeApplication.class, args);
    }

}
