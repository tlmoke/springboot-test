package com.tl.springboottest.runner;

import java.util.Arrays;

import org.jboss.logging.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 为了实现服务器启动即执行某些操作，只需要实现spring boot中的CommandLineRunner接口即可
 */
@Component
@Order(value = 1) // 设置启动执行顺序
public class MyCommandRunner1 implements CommandLineRunner {
    private Logger logger = Logger.getLogger(this.getClass());

    /**
     * 系统启动即会执行Run方法
     */
    @Override
    public void run(String... arg0) throws Exception {
        logger.info("执行启动任务1..." + Arrays.asList(arg0));
    }
}
