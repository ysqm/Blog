package com.elm.mapper;

import com.elm.annotation.AutoFill;
import com.elm.entity.User;
import com.elm.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from users where wechat_account = #{wechatAccount}")
    User getUserByWechatAccount(String wechatAccount);

    @Select("select * from users where qq_account = #{qqAccount}")
    User getUserByQqAccount(String qqAccount);

    @Select("select * from users where username = #{username}")
    User getUserByAccount(String username);

    @Select("select * from users where email = #{email}")
    User getUserByEmail(String email);

    @Select("select * from users where user_id = #{id}")
    User getUserById(int id);

    @Select("UPDATE users SET last_login_time = #{loginTime}")
    Integer loginTime(LocalDateTime loginTime);

    @AutoFill(value = OperationType.INSERT)
    Integer createUser(User user);

    @AutoFill(value = OperationType.UPDATE)
    Integer updateUser(User user);
}