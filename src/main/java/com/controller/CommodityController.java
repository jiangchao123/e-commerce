package com.controller;

import com.constant.PageSizeConstant;
import com.em.OperateEnum;
import com.entity.CommodityDO;
import com.mapper.CommodityDOMapper;
import com.service.CommodityService;
import com.util.Pager;
import com.vo.CommodityVO;
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
@RequestMapping("/commodity")
public class CommodityController {

    @Autowired
    private CommodityDOMapper commodityDOMapper;

    @Autowired
    private CommodityService commodityService;

    @RequestMapping("/{id}")
    public String view(@PathVariable("id") Long id, ModelMap modelMap) {
        CommodityDO commodity = commodityDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("commodity", commodity);
        return "/commodity/commodityInfo";
    }

    @RequestMapping("/commodityList/{page}")
    public String viewList(@PathVariable("page") Integer page, ModelMap modelMap) {
        Pager pager = new Pager(page, PageSizeConstant.pageSize);
        List<CommodityVO> commoditys = commodityService.searchCommoditysByPage(pager);
        modelMap.addAttribute("commoditys", commoditys);
        modelMap.addAttribute("pager", pager);
        return "/commodity/commodityList";
    }

    @RequestMapping("/edit/{id}")
    public String editCommodity(@PathVariable("id") Long id, ModelMap modelMap) {
        CommodityDO commodity = commodityDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("commodity", commodity);
        modelMap.addAttribute("operateEn", "edit/" + id);
        modelMap.addAttribute("operateCh", OperateEnum.UPDATE.code());
        return "/commodity/add";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editCommodity(@Valid CommodityDO commodityDO, BindingResult bindingResult, ModelMap modelMap) {
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("bindingResult",bindingResult);
            return "/commodity/add";
        }
        commodityDO.setUpdatetime(new Date(System.currentTimeMillis()));
        commodityDOMapper.updateByPrimaryKeySelective(commodityDO);
        return "redirect:/commodity/commodityList/1.vm";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addCommodity(ModelMap modelMap) {
        modelMap.addAttribute("commodity", new CommodityDO());
        modelMap.addAttribute("operateEn", "add");
        modelMap.addAttribute("operateCh", OperateEnum.ADD.code());
        return "/commodity/add";
    }


    //目前这一块有问题了
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addCommodity(@Valid CommodityDO commodityDO, BindingResult bindingResult, ModelMap modelMap) {
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("bindingResult",bindingResult);
            return "/commodity/add";
        }
        commodityDO.setShopId(5L);
        commodityDO.setCreatetime(new Date(System.currentTimeMillis()));
        commodityDO.setUpdatetime(new Date(System.currentTimeMillis()));
        commodityDOMapper.insert(commodityDO);
        return "redirect:/commodity/commodityList/1.vm";
    }
}
