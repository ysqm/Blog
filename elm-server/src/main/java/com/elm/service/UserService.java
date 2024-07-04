package com.elm.service;

import com.elm.dto.UpdateUserDTO;
import com.elm.dto.UserLoginDTO;
import com.elm.entity.User;
import com.elm.result.Result;

public interface UserService {

    Result addUser(UpdateUserDTO updateUserDTO);
    User login(UserLoginDTO userLoginDTO);
    Result updateUser(UpdateUserDTO updateUserDTO);
    Result logout(Integer userId);
}
