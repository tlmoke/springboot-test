package com.tl.springboottest.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.jboss.logging.Logger;

/**
 * 使用注解开发一个监听器
 */
@WebListener
public class Mylistener implements ServletContextListener {
    private Logger logger = Logger.getLogger(getClass());

    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        logger.info("Mylistener contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent contextEvent) {
        logger.info("Mylistener contextDestroyed");
    }

}
