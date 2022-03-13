package com.tl.springboottest.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching // 标注启动缓存
public class EhCacheConfig {
    /**
     * ehcache 主要的管理器
     * 
     * @param bean
     * @return
     */
    @Bean
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
        System.out.println("EhCacheConfig.ehCacheCacheManager()");
        return new EhCacheCacheManager(bean.getObject());
    }

    /*
     * 据 shared 与否的设置,
     * Spring 分别通过 CacheManager.create()
     * 或 new CacheManager()方式来创建一个 ehcache 基地.
     *
     * 也说是说通过这个来设置 cache 的基地是这里的 Spring 独用,还是跟别的(如 hibernate 的
     * Ehcache 共享)
     *
     */
    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        System.out.println("EhCacheConfig.ehCacheManagerFactoryBean()");
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        cacheManagerFactoryBean.setConfigLocation(new ClassPathResource("conf/ehcache.xml"));
        cacheManagerFactoryBean.setShared(true);
        return cacheManagerFactoryBean;
    }
}
