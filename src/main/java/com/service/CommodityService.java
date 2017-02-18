package com.service;

import com.convert.CommodityConverter;
import com.entity.CategoryDO;
import com.entity.CommodityDO;
import com.entity.CommodityDOExample;
import com.entity.ShopDO;
import com.mapper.CategoryDOMapper;
import com.mapper.CommodityDOMapper;
import com.mapper.ShopDOMapper;
import com.util.Pager;
import com.vo.CommodityVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiangchao08 on 17/2/3.
 */
@Service
public class CommodityService {

    @Autowired
    private CommodityDOMapper commodityDOMapper;

    @Autowired
    private ShopDOMapper shopDOMapper;

    @Autowired
    private CategoryDOMapper categoryDOMapper;

    public List<CommodityVO> searchCommoditysByPage(Pager pager) {
        CommodityDOExample commodityDOExample = new CommodityDOExample();
        pager.setCount(commodityDOMapper.countByExample(commodityDOExample));
        commodityDOExample.setLimitStart(pager.getBegin());
        commodityDOExample.setLimitEnd(pager.getLength());
        commodityDOExample.setOrderByClause("updatetime DESC");
        List<CommodityDO> commodityDOs = commodityDOMapper.selectByExample(commodityDOExample);
        List<CommodityVO> commodityVOs = new ArrayList<>();
        if (commodityDOs != null) {
            for (CommodityDO commodityDO : commodityDOs) {
                ShopDO shopDO = shopDOMapper.selectByPrimaryKey(commodityDO.getShopId());
                CategoryDO categoryDO = categoryDOMapper.selectByPrimaryKey(commodityDO.getCategoryId());
                CommodityVO commodityVO = CommodityConverter.convert(commodityDO, shopDO, categoryDO);
                commodityVOs.add(commodityVO);
            }
        }
        return commodityVOs;
    }
}
