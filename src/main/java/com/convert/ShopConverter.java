package com.convert;

import com.entity.ShopDO;
import com.entity.UserDO;
import com.vo.ShopVO;

/**
 * Created by jiangchao08 on 17/2/5.
 */
public class ShopConverter {

    public static ShopVO convert(ShopDO shopDO, UserDO userDO) {
        String userNickname = null;
        if (userDO != null) {
            userNickname = userDO.getNickname();
        }
        ShopVO shopVO = new ShopVO();
        shopVO.setId(shopDO.getId());
        shopVO.setUserId(shopDO.getUserId());
        shopVO.setUserNickname(userNickname);
        shopVO.setStatus(shopDO.getStatus());
        shopVO.setShopname(shopDO.getShopname());
        shopVO.setAddress(shopDO.getAddress());
        shopVO.setDescription(shopDO.getDescription());
        shopVO.setStatus(shopDO.getStatus());
        shopVO.setCreatetime(shopDO.getCreatetime());
        shopVO.setUpdatetime(shopDO.getUpdatetime());
        return shopVO;
    }

}
