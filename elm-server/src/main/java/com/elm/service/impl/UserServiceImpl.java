package com.elm.service.impl;

import com.elm.dto.UserLoginDTO;
import com.elm.entity.User;
import com.elm.mapper.UserMapper;
import com.elm.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


}
