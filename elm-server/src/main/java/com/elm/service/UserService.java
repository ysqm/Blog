package com.elm.service;

import com.elm.dto.UpdateUserDTO;
import com.elm.dto.UserLoginDTO;
import com.elm.entity.User;
import com.elm.result.Result;
import org.aspectj.weaver.patterns.IToken;

import java.util.Map;

public interface UserService {

    Result login(UserLoginDTO userLoginDTO);
    Result logout(IToken token);
    Result updateUser(UpdateUserDTO updateUserDTO);
    Result getUserById(Integer id);
    Result getUserByUsername(String username);
    Result getUserByEmail(String email);
    Result getUserByMobile(String mobile);
    Result getUserByQQ(String qq);
    Result addUser(UpdateUserDTO updateUserDTO);
    Result deleteUser(Integer id);

}
