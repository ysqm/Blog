package com.elm.service.impl;

import com.elm.constant.MessageConstant;
import com.elm.constant.StatusConstant;
import com.elm.dto.UpdateUserDTO;
import com.elm.dto.UserLoginDTO;
import com.elm.entity.User;
import com.elm.mapper.UserMapper;
import com.elm.result.Result;
import com.elm.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Result addUser(UpdateUserDTO updateUserDTO) {
        User user = new User();
        BeanUtils.copyProperties(updateUserDTO, user);
        user.setIsLoggedOut(StatusConstant.DISABLE);
        user.setLastLoginTime(LocalDateTime.now());
        user.setRegisterDate(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setId(null);
        User user1 = userMapper.getUserByAccount(user.getUsername());
        if(user1 != null) {
            return Result.error(MessageConstant.ACCOUNT_EXIST);
        }
        int msg = userMapper.createUser(user);
        return Result.success( );
    }

}
