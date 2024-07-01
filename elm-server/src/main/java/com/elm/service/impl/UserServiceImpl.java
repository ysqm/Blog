package com.elm.service.impl;

import com.elm.constant.MessageConstant;
import com.elm.constant.StatusConstant;
import com.elm.dto.UpdateUserDTO;
import com.elm.dto.UserLoginDTO;
import com.elm.entity.User;
import com.elm.exception.AccountLockedException;
import com.elm.exception.AccountNotFoundException;
import com.elm.exception.PasswordErrorException;
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
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        user.setUserId(null);
        User user1 = userMapper.getUserByAccount(user.getUsername());
        if(user1 != null) {
            return Result.error(MessageConstant.ACCOUNT_EXIST);
        }
        int msg = userMapper.createUser(user);
        return Result.success();
    }

    @Override
    public User login(UserLoginDTO userLoginDTO) {

        User user = userMapper.getUserByAccount(userLoginDTO.getUsername());

        if(user == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        String password = userLoginDTO.getPassword();

        // TODO 可以md5加密
        if(!user.getPassword().equals(password)) {
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if(StatusConstant.ENABLE.equals(user.getIsLoggedOut())) {
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        userMapper.loginTime(LocalDateTime.now());

        return user;
    }

}
