package com.controller;

import com.entity.CategoryDO;
import com.entity.CategoryDOExample;
import com.mapper.CategoryDOMapper;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryDOMapper categoryDOMapper;

    @RequestMapping("/{id}")
    public String view(@PathVariable("id") Integer id, ModelMap modelMap) {
        CategoryDO category = categoryDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("category", category);
        return "/category/categoryInfo";
    }

    @RequestMapping("/categoryList")
    public List<CategoryDO> viewList(ModelMap modelMap) {
        List<CategoryDO> categorys = categoryDOMapper.selectByExample(new CategoryDOExample());
        modelMap.addAttribute("categorys", categorys);
        return categorys;
    }

    @RequestMapping("/add")
    public List<CategoryDO> addUser(ModelMap modelMap, CategoryDO categoryDO) {
        if (categoryDO == null || categoryDO.getCategoryName() == null) return null;
        categoryDOMapper.insert(categoryDO);
        List<CategoryDO> categorys = categoryDOMapper.selectByExample(new CategoryDOExample());
        modelMap.addAttribute("categorys", categorys);
        return categorys;
    }
}
