package com.tl.springboottest.environment;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 读取spring以及系统环境变量 主要是@Configuration，实现接口：EnvironmentAware就能获取到系统环境信息;
 */
@Configuration
public class MyEnvironment implements EnvironmentAware {
    
    @Value("${spring.datasource.url}") // 使用el表达式读取spring主配置文件
    private String jdbcUrl;
    private Logger logger = Logger.getLogger(getClass());

    @Override
    public void setEnvironment(Environment environment) {
        // springEL表达式获取的值
        logger.info("springel表达式获取的值：" + jdbcUrl);
        // 获取系统属性：
        logger.info("JAVA_HOME：" + environment.getProperty("JAVA_HOME"));
        // 获取spring主配置文件中的属性
        logger.info("spring.datasource.url:" + environment.getProperty("spring.datasource.url"));
        // 获取前缀是“spring.datasource”的所有属性值
        // RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");
        // logger.info("通过前缀获取的url:" + propertyResolver.getProperty("url"));
        // logger.info("通过前缀获取的driverClassName:" + propertyResolver.getProperty("driverClassName"));
    }
}
