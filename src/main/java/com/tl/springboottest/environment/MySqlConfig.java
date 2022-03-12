package com.tl.springboottest.environment;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class MySqlConfig {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
