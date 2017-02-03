package com.service;

import com.entity.CommodityDO;
import com.entity.CommodityDOExample;
import com.mapper.CommodityDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangchao08 on 17/2/3.
 */
@Service
public class CommodityService {

    @Autowired
    private CommodityDOMapper commodityDOMapper;

    public List<CommodityDO> searchCommoditysByPage() {
        CommodityDOExample commodityDOExample = new CommodityDOExample();
        commodityDOExample.setOrderByClause("createtime DESC");
        List<CommodityDO> commoditys = commodityDOMapper.selectByExample(commodityDOExample);
        return commoditys;
    }
}
