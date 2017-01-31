package com.controller;

import com.entity.UserDO;
import com.entity.UserDOExample;
import com.mapper.UserDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by jiangchao08 on 16/12/5.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping("/{id}")
    public String view(@PathVariable("id") Long id, ModelMap modelMap) {
        UserDO user = userDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("user", user);
        return "/user/userInfo";
    }

    @RequestMapping("/userList")
    public List<UserDO> viewList(ModelMap modelMap) {
        List<UserDO> users = userDOMapper.selectByExample(new UserDOExample());
        modelMap.addAttribute("users", users);
        return users;
    }

    @RequestMapping("/add")
    public List<UserDO> addUser(ModelMap modelMap, UserDO userDO) {
        if (userDO == null || userDO.getUsername() == null) return null;
        userDOMapper.insert(userDO);
        List<UserDO> users = userDOMapper.selectByExample(new UserDOExample());
        modelMap.addAttribute("users", users);
        return users;
    }
}
