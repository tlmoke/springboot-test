package com.tl.springboottest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MySqlConfig.class)
public class MyDataConfiguration {
    @Autowired
    private MySqlConfig config;
}
