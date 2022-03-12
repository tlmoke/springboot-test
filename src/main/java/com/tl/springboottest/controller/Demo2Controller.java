package com.tl.springboottest.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Demo2Controller {

    @RequestMapping("/thymeleafHello")
    public String hello(Map<String, Object> map) {
        map.put("hello", "this data is from backing server");
        return "helloHtml";
    }

    @RequestMapping("/freemarkerHello")
    public String hello(ModelMap map) {
        map.put("message", "this data is from backing server , for freemarker");
        return "helloHtml1";
    }

    @RequestMapping("/freemarkerHello2")
    public String hello2(Map<String, Object> map) {
        map.put("message", "this data is from backing server , for freemarker2");
        return "helloHtml1";
    }

    // @RequestMapping("/helloJsp")
    // public String hello3(ModelMap map) {
    //     map.put("message", "this data is from the backing server");
    //     return "helloJsp";
    // }
}
