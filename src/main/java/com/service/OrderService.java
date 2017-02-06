package com.service;

import com.entity.OrderDO;
import com.entity.OrderDOExample;
import com.mapper.OrderDOMapper;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangchao08 on 17/2/3.
 */
@Service
public class OrderService {

    @Autowired
    private OrderDOMapper orderDOMapper;

    public List<OrderDO> searchOrdersByPage(Pager pager) {
        OrderDOExample orderDOExample = new OrderDOExample();
        pager.setCount(orderDOMapper.countByExample(orderDOExample));
        orderDOExample.setLimitStart(pager.getBegin());
        orderDOExample.setLimitEnd(pager.getLength());
        orderDOExample.setOrderByClause("createtime DESC");
        List<OrderDO> orders = orderDOMapper.selectByExample(orderDOExample);
        return orders;
    }

}
