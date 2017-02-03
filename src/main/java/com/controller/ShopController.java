package com.controller;

import com.em.OperateEnum;
import com.em.ShopStatusEnum;
import com.entity.ShopDO;
import com.mapper.ShopDOMapper;
import com.service.ShopService;
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
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopDOMapper shopDOMapper;
    
    @Autowired
    private ShopService shopService;

    @RequestMapping("/{id}")
    public String view(@PathVariable("id") Long id, ModelMap modelMap) {
        ShopDO shop = shopDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("shop", shop);
        return "/shop/shopInfo";
    }

    @RequestMapping("/shopList")
    public List<ShopDO> viewList(ModelMap modelMap) {
        List<ShopDO> shops = shopService.searchShopsByPage();
        modelMap.addAttribute("shops", shops);
        return shops;
    }

    @RequestMapping("/edit/{id}")
    public String editShop(@PathVariable("id") Long id, ModelMap modelMap) {
        ShopDO shop = shopDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("shop", shop);
        modelMap.addAttribute("operateEn", "edit/" + id);
        modelMap.addAttribute("operateCh", OperateEnum.UPDATE.code());
        return "/shop/add";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editShop(@Valid ShopDO shopDO, BindingResult bindingResult, ModelMap modelMap) {
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("bindingResult",bindingResult);
            return "/shop/add";
        }
        shopDO.setUpdatetime(new Date(System.currentTimeMillis()));
        shopDOMapper.updateByPrimaryKeySelective(shopDO);
        List<ShopDO> shops = shopService.searchShopsByPage();
        modelMap.addAttribute("shops", shops);
        return "redirect:/shop/shopList";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addShop(ModelMap modelMap) {
        modelMap.addAttribute("shop", new ShopDO());
        modelMap.addAttribute("operateEn", "add");
        modelMap.addAttribute("operateCh", OperateEnum.ADD.code());
        return "/shop/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addShop(@Valid ShopDO shopDO, BindingResult bindingResult, ModelMap modelMap) {
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("bindingResult",bindingResult);
            return "/shop/add";
        }
        shopDO.setCreatetime(new Date(System.currentTimeMillis()));
        shopDO.setStatus(ShopStatusEnum.NORMAL.code());
        shopDOMapper.insert(shopDO);
        List<ShopDO> shops = shopService.searchShopsByPage();
        modelMap.addAttribute("shops", shops);
        return "redirect:/shop/shopList";
    }
}
