package com.controller;

import com.constant.PageSizeConstant;
import com.em.OperateEnum;
import com.entity.AdminDO;
import com.mapper.AdminDOMapper;
import com.service.AdminService;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by jiangchao08 on 16/12/5.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminDOMapper adminDOMapper;

    @Autowired
    private AdminService adminService;

    @RequestMapping("/{id}")
    public String view(@PathVariable("id") Long id, ModelMap modelMap) {
        AdminDO admin = adminDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("admin", admin);
        return "/admin/adminInfo";
    }

    @RequestMapping("/adminList/{page}")
    public String viewList(@PathVariable("page") Integer page, ModelMap modelMap) {
        Pager pager = new Pager(page, PageSizeConstant.pageSize);
        List<AdminDO> admins = adminService.searchAdminsByPage(pager);
        modelMap.addAttribute("admins", admins);
        modelMap.addAttribute("pager", pager);
        return "/admin/adminList";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addAdmin(ModelMap modelMap) {
        modelMap.addAttribute("admin", new AdminDO());
        modelMap.addAttribute("operateEn", "add");
        modelMap.addAttribute("operateCh", OperateEnum.ADD.code());
        return "/admin/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAdmin(@Valid AdminDO adminDO, BindingResult bindingResult, ModelMap modelMap) {
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("bindingResult",bindingResult);
            return "/admin/add";
        }
        adminDOMapper.insert(adminDO);
        return "redirect:/admin/adminList";
    }
}
