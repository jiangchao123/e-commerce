package com.service;

import com.em.UserStatusEnum;
import com.entity.UserDO;
import com.entity.UserDOExample;
import com.mapper.UserDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jiangchao08 on 17/2/3.
 */
@Service
public class UserService {

    @Autowired
    private UserDOMapper userDOMapper;

    public List<UserDO> searchUsersByPage() {
        UserDOExample userDOExample = new UserDOExample();
        userDOExample.setOrderByClause("createtime DESC");
        List<UserDO> users = userDOMapper.selectByExample(userDOExample);
        return users;
    }

    public List<UserDO> searchAllUsers() {
        UserDOExample userDOExample = new UserDOExample();
        userDOExample.setOrderByClause("nickname ASC");
        UserDOExample.Criteria criteria = userDOExample.createCriteria();
        criteria.andStatusEqualTo(UserStatusEnum.NORMAL.code());
        List<UserDO> users = userDOMapper.selectByExample(userDOExample);
        return users;
    }
}
