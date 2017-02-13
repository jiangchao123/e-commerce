package com.service;

import com.entity.CategoryDO;
import com.entity.CategoryDOExample;
import com.mapper.CategoryDOMapper;
import com.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangchao08 on 17/2/3.
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryDOMapper categoryDOMapper;

    public List<CategoryDO> searchCategorysByPage(Pager pager) {
        CategoryDOExample categoryDOExample = new CategoryDOExample();
        pager.setCount(categoryDOMapper.countByExample(categoryDOExample));
        categoryDOExample.setLimitStart(pager.getBegin());
        categoryDOExample.setLimitEnd(pager.getLength());
        categoryDOExample.setOrderByClause("createtime DESC");
        List<CategoryDO> categorys = categoryDOMapper.selectByExample(categoryDOExample);
        return categorys;
    }
}
