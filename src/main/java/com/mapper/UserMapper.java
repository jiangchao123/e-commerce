package com.mapper;

import com.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by jiangchao08 on 16/12/10.
 */
@Mapper
public interface UserMapper {

    int save(User user);

    User selectById(Integer id);

    int updateById(User user);

    int deleteById(Integer id);

    List<User> queryAll();
}
