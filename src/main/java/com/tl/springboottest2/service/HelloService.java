package com.tl.springboottest2.service;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

// @Service("helloService")
public class HelloService {
    private Logger logger = Logger.getLogger(getClass());

    public void hello() {
        logger.info("这个bean是springboot默认情况下无法扫描到的");
    }
}
