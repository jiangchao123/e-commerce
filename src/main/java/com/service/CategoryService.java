package com.service;

import com.entity.CategoryDO;
import com.entity.CategoryDOExample;
import com.mapper.CategoryDOMapper;
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

    public List<CategoryDO> searchCategorysByPage() {
        CategoryDOExample categoryDOExample = new CategoryDOExample();
        categoryDOExample.setOrderByClause("createtime DESC");
        List<CategoryDO> categorys = categoryDOMapper.selectByExample(categoryDOExample);
        return categorys;
    }
}
