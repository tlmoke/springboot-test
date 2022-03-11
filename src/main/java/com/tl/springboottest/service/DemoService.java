package com.tl.springboottest.service;

import javax.transaction.Transactional;

import com.tl.springboottest.bean.Demo;
import com.tl.springboottest.dao.DemoDaoUseJdbcTemplate;
import com.tl.springboottest.dao.IDemoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("demoService") // 定义业务逻辑层
public class DemoService {

    @Autowired // 自动装配DAO
    private IDemoRepository demoRepository;
    @Autowired
    private DemoDaoUseJdbcTemplate demoDaoUseJdbcTemplate;

    @Transactional // 自动事务托管
    public void save(Demo demo) {
        demoRepository.save(demo);
    }

    public Demo getById(Integer id) {
        return demoDaoUseJdbcTemplate.getById(id);
    }
}
