package com.controller;

import com.entity.ShopDO;
import com.entity.ShopDOExample;
import com.entity.UserDO;
import com.entity.UserDOExample;
import com.mapper.ShopDOMapper;
import com.mapper.UserDOMapper;
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
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopDOMapper shopDOMapper;

    @RequestMapping("/{id}")
    public String view(@PathVariable("id") Long id, ModelMap modelMap) {
        ShopDO shop = shopDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("shop", shop);
        return "/shop/shopInfo";
    }

    @RequestMapping("/shopList")
    public List<ShopDO> viewList(ModelMap modelMap) {
        List<ShopDO> shops = shopDOMapper.selectByExample(new ShopDOExample());
        modelMap.addAttribute("shops", shops);
        return shops;
    }

    @RequestMapping("/add")
    public List<ShopDO> addUser(ModelMap modelMap, ShopDO shopDO) {
        if (shopDO == null || shopDO.getShopname() == null) return null;
        shopDOMapper.insert(shopDO);
        List<ShopDO> shops = shopDOMapper.selectByExample(new ShopDOExample());
        modelMap.addAttribute("shops", shops);
        return shops;
    }
}
