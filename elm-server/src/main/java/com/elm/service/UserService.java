package com.elm.service;

import com.elm.dto.AccountPageQueryDTO;
import com.elm.dto.UpdateUserDTO;
import com.elm.dto.UserLoginDTO;
import com.elm.entity.User;
import com.elm.result.PageResult;
import com.elm.result.Result;

public interface UserService {

    Result addUser(UpdateUserDTO updateUserDTO);
    User login(UserLoginDTO userLoginDTO);
    Result updateUser(UpdateUserDTO updateUserDTO);
    PageResult pageQuery(AccountPageQueryDTO accountPageQueryDTO);
}
