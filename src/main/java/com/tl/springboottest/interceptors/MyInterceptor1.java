package com.tl.springboottest.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 使用常规的方式开发springmvc拦截器1
 */
public class MyInterceptor1 implements HandlerInterceptor {
    private Logger logger = Logger.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object)
            throws Exception {
        logger.info("MyInterceptor1 preHandle...");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
            ModelAndView modelAndView) throws Exception {
        logger.info("MyInterceptor1 postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
            Exception exception) throws Exception {
        logger.info("MyInterceptor1 afterCompletion...");
    }

}
