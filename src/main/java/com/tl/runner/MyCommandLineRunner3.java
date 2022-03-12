package com.tl.runner;

import org.jboss.logging.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyCommandLineRunner3 implements CommandLineRunner {
    private Logger logger = Logger.getLogger(getClass());

    @Override
    public void run(String... arg0) throws Exception {
        logger.info("项目启动执行任务3");
    }
}
