package com.controller;

import com.em.OperateEnum;
import com.entity.OrderDO;
import com.mapper.OrderDOMapper;
import com.service.OrderService;
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
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private OrderService orderService;

    @RequestMapping("/{id}")
    public String view(@PathVariable("id") Long id, ModelMap modelMap) {
        OrderDO order = orderDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("order", order);
        return "/order/orderInfo";
    }

    @RequestMapping("/edit/{id}")
    public String editOrder(@PathVariable("id") Long id, ModelMap modelMap) {
        OrderDO order = orderDOMapper.selectByPrimaryKey(id);
        modelMap.addAttribute("order", order);
        modelMap.addAttribute("operateEn", "edit/" + id);
        modelMap.addAttribute("operateCh", OperateEnum.UPDATE.code());
        return "/order/add";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editOrder(@Valid OrderDO orderDO, BindingResult bindingResult, ModelMap modelMap) {
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("bindingResult",bindingResult);
            return "/order/add";
        }
        orderDO.setUpdatetime(new Date(System.currentTimeMillis()));
        orderDOMapper.updateByPrimaryKeySelective(orderDO);
        List<OrderDO> orders = orderService.searchOrdersByPage();
        modelMap.addAttribute("orders", orders);
        return "redirect:/order/orderList";
    }

    @RequestMapping("/orderList")
    public List<OrderDO> viewList(ModelMap modelMap) {
        List<OrderDO> orders = orderService.searchOrdersByPage();
        modelMap.addAttribute("orders", orders);
        return orders;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addOrder(ModelMap modelMap) {
        modelMap.addAttribute("order", new OrderDO());
        modelMap.addAttribute("operateEn", "add");
        modelMap.addAttribute("operateCh", OperateEnum.ADD.code());
        return "/order/add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addOrder(@Valid OrderDO orderDO, BindingResult bindingResult, ModelMap modelMap) {
        if(bindingResult.hasErrors()){
            modelMap.addAttribute("bindingResult",bindingResult);
            return "/order/add";
        }
        orderDO.setCreatetime(new Date(System.currentTimeMillis()));
        orderDOMapper.insert(orderDO);
        List<OrderDO> orders = orderService.searchOrdersByPage();
        modelMap.addAttribute("orders", orders);
        return "redirect:/order/orderList";
    }
}
