package com.controller;

import com.entity.AdminDO;
import com.service.AdminService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by jiangchao08 on 16/12/10.
 */
@Controller
public class IndexController {

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/index")
    public String hello(ModelMap modelMap) {
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String hello(ModelMap modelMap, AdminDO adminDO, HttpSession session) {
        if(adminDO.getPassword().equals(adminService.selectByAdminName(adminDO.getAdminname()).getPassword())) {
            modelMap.addAttribute("admin",adminDO);
            session.setAttribute("admin",adminDO);
            return "index";
        }
        else
            return "login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String adminLogin(ModelMap modelMap){
        return "login";
    }

}
