package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jiangchao08 on 16/12/10.
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/index")
    public String hello(ModelMap modelMap) {
        return "index";
    }
}
