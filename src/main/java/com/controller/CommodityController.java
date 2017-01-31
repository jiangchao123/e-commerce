package com.controller;

import com.entity.CommodityDO;
import com.entity.CommodityDOExample;
import com.mapper.CommodityDOMapper;
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
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityDOMapper commodityDOMapper;

    @RequestMapping("/{id}")
    public String view(@PathVariable("id") Long id, ModelMap modelMap) {
        CommodityDO commodity = commodityDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("commodity", commodity);
        return "/commodity/commodityInfo";
    }

    @RequestMapping("/commodityList")
    public List<CommodityDO> viewList(ModelMap modelMap) {
        List<CommodityDO> commoditys = commodityDOMapper.selectByExample(new CommodityDOExample());
        modelMap.addAttribute("commoditys", commoditys);
        return commoditys;
    }

    @RequestMapping("/add")
    public List<CommodityDO> addUser(ModelMap modelMap, CommodityDO commodityDO) {
        if (commodityDO == null || commodityDO.getCommodityName() == null) return null;
        commodityDOMapper.insert(commodityDO);
        List<CommodityDO> commoditys = commodityDOMapper.selectByExample(new CommodityDOExample());
        modelMap.addAttribute("commoditys", commoditys);
        return commoditys;
    }
}
