package com.tl.springboottest.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.jboss.logging.Logger;

//使用注解定义一个过滤器
@WebFilter(urlPatterns = "/*", filterName = "myFilter")
public class MyFilter implements Filter {
    private Logger logger = Logger.getLogger(getClass());

    @Override
    public void init(FilterConfig config) throws ServletException {
        logger.info("MyFilter.init()");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        logger.info("MyFilter.doFilter()");
        logger.info("before filter");
        chain.doFilter(request, response);
        logger.info("after filter");
    }

    @Override
    public void destroy() {
        logger.info("MyFilter.destroy()");
    }
}
