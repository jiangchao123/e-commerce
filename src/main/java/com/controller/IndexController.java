package com.controller;

import com.entity.AdminDO;
import com.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

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
        AdminDO admin = adminService.selectByAdminName(adminDO.getAdminname());//获取管理员各属性
        if(admin != null && adminDO.getPassword().equals(admin.getPassword())) {//判断密码是否相同
            session.setAttribute("admin",adminDO);//如果相同，放入model中
            return "redirect:/index";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String adminLogin(ModelMap modelMap){
        return "login";
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public  String adminResgister(AdminDO adminDO,ModelMap modelMap){
        AdminDO ado=adminService.selectByAdminName(adminDO.getAdminname());//获取管理员各属性
        if(ado==null)
        {
            adminDO.setCreatetime(new Date(System.currentTimeMillis()));
            adminService.insertAdminDo(adminDO);//如果尚未注册则注册
            modelMap.addAttribute("msg","成功注册");
            return "login";
        }
        else
        {
            modelMap.addAttribute("msg","用户名已经存在");
            return "login";//如果已注册则需重新注册(该步骤不清楚是否需要重新写页面，留待研究）
        }

    }
    //****************
    //接口编写
    //****************


}
