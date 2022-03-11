package com.tl.springboottest.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jboss.logging.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration // 声明类为系统配置类
@EnableScheduling // 开启调度任务
public class MyScheduleConfig {

    private Logger logger = Logger.getLogger(getClass());

    @Scheduled(cron = "0/30 * * * * ? ") // 定义调度器
    public void job1() {
        logger.info("this is my first job execute：" + new SimpleDateFormat("yyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
