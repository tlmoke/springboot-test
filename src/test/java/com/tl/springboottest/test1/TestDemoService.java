package com.tl.springboottest.test1;

import com.tl.springboottest.bean.Demo;
import com.tl.springboottest.service.DemoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestDemoService {
    // 自动装配业务逻辑层
    @Autowired
    private DemoService demoService;

    @Test
    public void testGetDemo() {
        demoService.getById(1);
    }

    @Test
    public void testSaveDemo() {
        demoService.save(new Demo("tl_" + Math.random()));
    }
}
