package com.tl.springboottest.dao;

import com.tl.springboottest.bean.Demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("daoUseJdbcTemplate")
public class DemoDaoUseJdbcTemplate {
    @Autowired // 自动装配模板
    private JdbcTemplate jdbcTemplate;

    public Demo getById(Integer id) {
        String sql = "select * from Demo where id = ?";
        // 获取数据映射
        RowMapper<Demo> mapper = new BeanPropertyRowMapper<>(Demo.class);
        return jdbcTemplate.queryForObject(sql, mapper, id);
    }
}
