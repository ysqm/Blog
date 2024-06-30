package com.elm.mapper;

import com.elm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUserByWechatAccount(String wechatAccount);

    User getUserByQqAccount(String qqAccount);

    @Select("select * from user where username = #{username}")
    User getUserByAccount(String username);

    @Select("select * from user where user_id = #{id}")
    User getUserById(int id);

    int createUser(User user);

    int updateUser(User user);
}