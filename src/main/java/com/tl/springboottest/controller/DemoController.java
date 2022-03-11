package com.tl.springboottest.controller;

import com.tl.springboottest.bean.Demo;
import com.tl.springboottest.service.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping("/getDemo")
    public Demo getDemo() {
        Demo demo = new Demo();
        demo.setId(1);
        demo.setName("Zjs");
        System.out.println(demo);
        return demo;
    }

    @RequestMapping("/zeroException")
    public int zeroException() {
        return 100 / 0;
    }

    @RequestMapping("/save")
    public String save() {
        demoService.save(new Demo("tl_" + Math.random()));
        return "ok the data was saved";
    }

    @RequestMapping("/show/{no}")
    public Demo showDemo2(@PathVariable(name = "no", required = true) Integer id) {
        System.out.println("showDemo2：" + id);
        return demoService.getById(id);
    }

    @RequestMapping("/show")
    public Demo showDemo(@RequestParam(name = "no", defaultValue = "1", required = true) Integer id) {
        System.out.println("showDemo：" + id);
        return demoService.getById(id);
    }


}
