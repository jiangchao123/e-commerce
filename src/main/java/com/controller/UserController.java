package com.controller;

import com.em.OperateEnum;
import com.em.UserStatusEnum;
import com.entity.UserDO;
import com.mapper.UserDOMapper;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by jiangchao08 on 16/12/5.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDOMapper userDOMapper;

    @Autowired
    private UserService userService;

    @RequestMapping("/{id}")
    public String view(@PathVariable("id") Long id, ModelMap modelMap) {
        UserDO user = userDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("user", user);
        return "/user/userInfo";
    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, ModelMap modelMap) {
        UserDO user = userDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("user", user);
        modelMap.addAttribute("operateEn", "edit/" + id);
        modelMap.addAttribute("operateCh", OperateEnum.UPDATE.code());
        return "/user/add";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editUser(@Valid UserDO userDO, BindingResult bindingResult, ModelMap modelMap) {
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("bindingResult",bindingResult);
            return "/user/add";
        }
        userDO.setUpdatetime(new Date(System.currentTimeMillis()));
        userDOMapper.updateByPrimaryKeySelective(userDO);
        List<UserDO> users = userService.searchUsersByPage();
        modelMap.addAttribute("users", users);
        return "redirect:/user/userList";
    }

    @RequestMapping("/userList")
    public List<UserDO> viewList(ModelMap modelMap) {
        List<UserDO> users = userService.searchUsersByPage();
        modelMap.addAttribute("users", users);
        return users;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUser(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDO());
        modelMap.addAttribute("operateEn", "add");
        modelMap.addAttribute("operateCh", OperateEnum.ADD.code());
        return "/user/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@Valid UserDO userDO, BindingResult bindingResult, ModelMap modelMap) {
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("bindingResult",bindingResult);
            return "/user/add";
        }
        userDO.setCreatetime(new Date(System.currentTimeMillis()));
        userDO.setStatus(UserStatusEnum.NORMAL.code());
        userDOMapper.insert(userDO);
        List<UserDO> users = userService.searchUsersByPage();
        modelMap.addAttribute("users", users);
        return "redirect:/user/userList";
    }
}
