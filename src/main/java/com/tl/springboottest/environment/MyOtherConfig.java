package com.tl.springboottest.environment;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "custom")
public class MyOtherConfig {
    private String name;
    private String gender;
}
