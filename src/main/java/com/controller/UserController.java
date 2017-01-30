package com.controller;

import com.entity.User;
import com.entity.UserDO;
import com.entity.UserDOExample;
import com.mapper.UserDOMapper;
import com.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jiangchao08 on 16/12/5.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserDOMapper userDOMapper;

    @RequestMapping("/{id}")
    public User view(@PathVariable("id") Long id) {
//        User user = new User();
//        user.setId(id);
//        user.setName("jiangchao");
        User user = mapper.selectById(1);
        return user;
    }

    @RequestMapping("/userList")
    public List<UserDO> viewList(ModelMap modelMap) {
        List<UserDO> users = userDOMapper.selectByExample(new UserDOExample());
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("test", "testABC");
        return users;
    }
}
