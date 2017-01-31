package com.controller;

import com.entity.AdminDO;
import com.entity.AdminDOExample;
import com.mapper.AdminDOMapper;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminDOMapper adminDOMapper;

    @RequestMapping("/{id}")
    public String view(@PathVariable("id") Long id, ModelMap modelMap) {
        AdminDO admin = adminDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("admin", admin);
        return "/admin/adminInfo";
    }

    @RequestMapping("/adminList")
    public List<AdminDO> viewList(ModelMap modelMap) {
        List<AdminDO> admins = adminDOMapper.selectByExample(new AdminDOExample());
        modelMap.addAttribute("admins", admins);
        return admins;
    }

    @RequestMapping("/add")
    public List<AdminDO> addUser(ModelMap modelMap, AdminDO adminDO) {
        if (adminDO == null || adminDO.getAdminname() == null) return null;
        adminDOMapper.insert(adminDO);
        List<AdminDO> admins = adminDOMapper.selectByExample(new AdminDOExample());
        modelMap.addAttribute("admins", admins);
        return admins;
    }
}
