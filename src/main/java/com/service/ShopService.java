package com.service;

import com.convert.ShopConverter;
import com.entity.ShopDO;
import com.entity.ShopDOExample;
import com.entity.UserDO;
import com.mapper.ShopDOMapper;
import com.mapper.UserDOMapper;
import com.vo.ShopVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangchao08 on 17/2/3.
 */
@Service
public class ShopService {
    
    @Autowired
    private ShopDOMapper shopDOMapper;

    @Autowired
    private UserDOMapper userDOMapper;

    public List<ShopVO> searchShopsByPage() {
        ShopDOExample shopDOExample = new ShopDOExample();
        shopDOExample.setOrderByClause("createtime DESC");
        List<ShopDO> shopDOS = shopDOMapper.selectByExample(shopDOExample);
        List<ShopVO> shopVOS = new ArrayList<>();
        if (shopDOS != null) {
            for (ShopDO shopDO : shopDOS) {
                UserDO userDO = userDOMapper.selectByPrimaryKey(shopDO.getUserId());
                ShopVO shopVO = ShopConverter.convert(shopDO, userDO);
                shopVOS.add(shopVO);
            }
        }
        return shopVOS;
    }
}
