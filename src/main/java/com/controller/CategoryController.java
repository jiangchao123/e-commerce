package com.controller;

import com.constant.PageSizeConstant;
import com.em.OperateEnum;
import com.entity.CategoryDO;
import com.entity.CategoryDOExample;
import com.mapper.CategoryDOMapper;
import com.service.CategoryService;
import com.util.Pager;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryDOMapper categoryDOMapper;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/{id}")
    public String view(@PathVariable("id") Integer id, ModelMap modelMap) {
        CategoryDO category = categoryDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("category", category);
        return "/category/categoryInfo";
    }



    @RequestMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") int id, ModelMap modelMap) {
        CategoryDO categoryDO = categoryDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("category",categoryDO);
        modelMap.addAttribute("operateEn", "edit/" + id);
        modelMap.addAttribute("operateCh", OperateEnum.UPDATE.code());
        return "/category/add";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editUser(@Valid CategoryDO categoryDO,BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("bindingResult", bindingResult);
            return "/category/add";
        }
        categoryDO.setCreatetime(new Date(System.currentTimeMillis()));
        categoryDOMapper.updateByPrimaryKeySelective(categoryDO);
        return "redirect:/category/categoryList/1";
    }


    @RequestMapping("/categoryList/{page}")
    public String viewList(@PathVariable("page") Integer page,ModelMap modelMap) {
        Pager pager = new Pager(page, PageSizeConstant.pageSize);
        List<CategoryDO> categorys = categoryService.searchCategorysByPage(pager);
        modelMap.addAttribute("categorys", categorys);
        modelMap.addAttribute("pager",pager);
        return "/category/categoryList";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCategory(ModelMap modelMap) {
        modelMap.addAttribute("category", new CategoryDO());
        modelMap.addAttribute("operateEn", "add");
        modelMap.addAttribute("operateCh", OperateEnum.ADD.code());
        return "/category/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCategory(@Valid CategoryDO categoryDO, BindingResult bindingResult, ModelMap modelMap) {
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("bindingResult",bindingResult);
            return "/category/add";
        }
        categoryDO.setCreatetime(new Date(System.currentTimeMillis()));
        categoryDOMapper.insert(categoryDO);
        return "redirect:/category/categoryList/1";
    }
}
