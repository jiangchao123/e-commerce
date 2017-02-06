package com.service;

import com.entity.AdminDO;
import com.entity.AdminDOExample;
import com.mapper.AdminDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangchao08 on 17/2/3.
 */
@Service
public class AdminService {

    @Autowired
    private AdminDOMapper adminDOMapper;

    public List<AdminDO> searchAdminsByPage() {
        AdminDOExample adminDOExample = new AdminDOExample();
        adminDOExample.setOrderByClause("createtime DESC");
        List<AdminDO> admins = adminDOMapper.selectByExample(adminDOExample);
        return admins;
    }

    //按管理员名查找对应管理员
    public AdminDO selectByAdminName(String adminName){
        AdminDOExample adminDOExample = new AdminDOExample();
        AdminDOExample.Criteria criteria = adminDOExample.createCriteria();
        criteria.andAdminnameEqualTo(adminName);
        List<AdminDO> adminDOs = adminDOMapper.selectByExample(adminDOExample);
        if (adminDOs == null) {
            return null;
        }
        return adminDOs.get(0);
    }

    public boolean insertAdminDo(AdminDO adminDO){
        adminDOMapper.insert(adminDO);
        return true;
    }
}
