package com.tl.springboottest.dao;

import com.tl.springboottest.bean.Demo;

import org.springframework.data.repository.CrudRepository;

/**
 * Demo表的DAO层接口，并没有具体的实现，继承基础CRUD实现
 * 泛型1：实体类 泛型2：主键映射类型
 */
public interface IDemoRepository  extends CrudRepository<Demo, Integer> {
    
}
