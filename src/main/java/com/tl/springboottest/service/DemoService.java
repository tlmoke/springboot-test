package com.tl.springboottest.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.tl.springboottest.bean.Demo;
import com.tl.springboottest.dao.DemoDaoUseJdbcTemplate;
import com.tl.springboottest.dao.IDemoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service("demoService") // 定义业务逻辑层
public class DemoService {

    @Autowired // 自动装配DAO
    private IDemoRepository demoRepository;
    @Autowired
    private DemoDaoUseJdbcTemplate demoDaoUseJdbcTemplate;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Transactional // 自动事务托管
    public void save(Demo demo) {
        demoRepository.save(demo);
    }

    @Cacheable(value = "demo")
    public Demo getById(Integer id) {
        System.err.println("DemoService.getById()=========从数据库中进行获取的....id=" + id);
        return demoDaoUseJdbcTemplate.getById(id);
    }

    @CacheEvict(value = "demo")
    public void deleteFromCache(Integer id) {
        System.out.println("DemoService.deleteFromCache().从缓存中删除....id=" + id);
    }

}
