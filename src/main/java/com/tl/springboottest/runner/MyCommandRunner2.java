package com.tl.springboottest.runner;

import java.util.Arrays;

import com.tl.springboottest.environment.MyOtherConfig;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 为了实现服务器启动即执行某些操作，只需要实现spring boot中的CommandLineRunner接口即可
 */
@Component
@Order(value = 2) // 设置启动执行顺序
public class MyCommandRunner2 implements CommandLineRunner {

    @Autowired
    private MyOtherConfig config;
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * 系统启动即会执行Run方法
     */
    @Override
    public void run(String... arg0) throws Exception {
        logger.info("执行启动任务2..." + Arrays.asList(arg0));
        logger.info(config);
    }
}
