package com.elm.mapper;

import com.elm.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User getUserByWechatAccount(String wechatAccount);

    User getUserByQqAccount(String qqAccount);

    User getUserByAccount(String username);

    int createUser(User user);

    int updateUser(User user);
}