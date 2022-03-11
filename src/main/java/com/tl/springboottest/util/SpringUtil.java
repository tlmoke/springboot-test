package com.tl.springboottest.util;

import org.jboss.logging.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring工具类，为了更方便的获取spring的applicationContext 直接实现接口ApplicationContextAware
 */
@Component
public class SpringUtil implements ApplicationContextAware {

    private Logger logger = Logger.getLogger(getClass());
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
        logger.info(
                "========ApplicationContext配置成功,在普通类可以通过调用SpringUtils.getApplicationContext()获取applicationContext对象,applicationContext="
                        + SpringUtil.applicationContext + "========");
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static <T> Object getBean(Class<T> class1) {
        return applicationContext.getBean(class1);
    }

    public static <T> Object getBean(Class<T> class1, String beanName) {
        return applicationContext.getBean(class1, beanName);
    }
}
