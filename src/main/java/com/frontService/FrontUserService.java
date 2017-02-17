package com.frontService;

import com.entity.UserDO;
import com.entity.UserDOExample;
import com.mapper.UserDOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * Created by sa on 2017-02-15.
 */
@Service
public class FrontUserService {

     @Autowired
    private UserDOMapper userDOMapper;


     public UserDO selectUserByUserName(String userName){
         UserDOExample userDOExample= new UserDOExample();
         UserDOExample.Criteria criteria=userDOExample.createCriteria();
         criteria.andUsernameEqualTo(userName);
         List<UserDO> users=userDOMapper.selectByExample(userDOExample);
         if(users.size()==0)
             return  null;
         return users.get(0);
     }
}
