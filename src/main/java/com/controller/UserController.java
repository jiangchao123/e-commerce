package com.controller;

import com.entity.User;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jiangchao08 on 16/12/5.
 */
//@EnableAutoConfiguration
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper mapper;

    @RequestMapping("/{id}")
    public User view(@PathVariable("id") Long id) {
//        User user = new User();
//        user.setId(id);
//        user.setName("jiangchao");
        User user = mapper.selectById(1);
        return user;
    }
}
