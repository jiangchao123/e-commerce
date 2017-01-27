package com;

import com.Application;
import com.entity.User;
import com.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jiangchao08 on 16/12/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@ActiveProfiles
public class AppTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("张三");
        user.setAge(23);
        mapper.save(user);
        System.out.println("插入用户信息"+user.getName());
    }
}
