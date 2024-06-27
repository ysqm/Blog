package com.elm.controller.admin;

import com.elm.dto.UpdateUserDTO;
import com.elm.dto.UserLoginDTO;
import com.elm.entity.User;
import com.elm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Map<String, Object> register(UpdateUserDTO updateUserDTO) {
        return userService.addUser(updateUserDTO);
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Map<String, Object> login(UserLoginDTO user) {return userService.login();}

    @PostMapping("/update")
    @ApiOperation("用户信息修改")
    public Map<String, Object> update(UpdateUserDTO updateUserDTO)
    {
        return userService.UpdateUser(user);
    }

    @PostMapping("/getUserById")
    @ApiOperation("通过Id获取用户信息")
    public Map<String, Object> getUserById(Integer id) {return userService.getUserById(id);}

    @PostMapping("/testadduser")
    @ApiOperation("添加用户")
    public Map<String, Object> testadduser(Integer num) {return userService.Adduser(num);}
}
