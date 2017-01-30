package com.controller;

import com.entity.UserDO;
import com.entity.UserDOExample;
import com.mapper.UserDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by jiangchao08 on 16/12/10.
 */
@Controller
public class HelloController {

    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping(value = "/test")
    public String hello(ModelMap modelMap) {
        modelMap.addAttribute("message", "hello world!");
        System.out.println("===========================");
        return "test";
    }

    @RequestMapping("/userList")
    public List<UserDO> viewList(ModelMap modelMap) {
        List<UserDO> users = userDOMapper.selectByExample(new UserDOExample());
        modelMap.addAttribute("users", users);
        return users;
    }
}
