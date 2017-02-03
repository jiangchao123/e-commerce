package com.service;

import com.entity.ShopDO;
import com.entity.ShopDOExample;
import com.mapper.ShopDOMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by jiangchao08 on 17/2/3.
 */
public class ShopService {
    
    @Autowired
    private ShopDOMapper shopDOMapper;

    public List<ShopDO> searchShopsByPage() {
        ShopDOExample shopDOExample = new ShopDOExample();
        shopDOExample.setOrderByClause("createtime DESC");
        List<ShopDO> shops = shopDOMapper.selectByExample(shopDOExample);
        return shops;
    }
}
