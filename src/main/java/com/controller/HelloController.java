package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jiangchao08 on 16/12/10.
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/test")
    public String hello(ModelMap modelMap) {
        modelMap.addAttribute("message", "hello world!");
        System.out.println("===========================");
        return "test";
    }
}
