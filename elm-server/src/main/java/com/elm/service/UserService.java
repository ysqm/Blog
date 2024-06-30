package com.elm.service;

import com.elm.dto.UpdateUserDTO;
import com.elm.dto.UserLoginDTO;
import com.elm.entity.User;
import com.elm.result.Result;
import org.aspectj.weaver.patterns.IToken;

import java.util.Map;

public interface UserService {

    Result addUser(UpdateUserDTO updateUserDTO);
}
