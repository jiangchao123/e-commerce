package com.controller;

import com.em.OperateEnum;
import com.entity.CategoryDO;
import com.entity.CategoryDOExample;
import com.mapper.CategoryDOMapper;
import com.service.CategoryService;
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

    @RequestMapping("/categoryList")
    public List<CategoryDO> viewList(ModelMap modelMap) {
        List<CategoryDO> categorys = categoryDOMapper.selectByExample(new CategoryDOExample());
        modelMap.addAttribute("categorys", categorys);
        return categorys;
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
        List<CategoryDO> categorys = categoryService.searchCategorysByPage();
        modelMap.addAttribute("categorys", categorys);
        return "redirect:/category/categoryList";
    }
}
