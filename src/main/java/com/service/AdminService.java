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

    public AdminDO selectByAdminName(String adminName){
        AdminDO adminDO=adminDOMapper.selectByAdminName(adminName);
        return adminDO;
    }

    public boolean insertAdminDo(AdminDO adminDO){
        adminDOMapper.insert(adminDO);
        return true;
    }
}
