package com.elm.controller.admin;

import com.elm.constant.JwtClaimsConstant;
import com.elm.dto.UpdateUserDTO;
import com.elm.dto.UserLoginDTO;
import com.elm.entity.User;
import com.elm.properties.JwtProperties;
import com.elm.result.Result;
import com.elm.service.UserService;
import com.elm.utils.JwtUtil;
import com.elm.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(tags = "用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    @PostMapping("/register")
    @ApiOperation("用户注册,传入的id都会被置为null")
    public Result register(@RequestBody UpdateUserDTO updateUserDTO) {
        log.info("新增用户：{}", updateUserDTO);
        return userService.addUser(updateUserDTO);
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("用户登录：{}", userLoginDTO);
        User user = userService.login(userLoginDTO);
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getUserId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims
                );
        UserLoginVO userLoginVO = new UserLoginVO();
        BeanUtils.copyProperties(user, userLoginVO);
        userLoginVO.setToken(token);
        return Result.success(userLoginVO);
    }

    @PostMapping("/edit")
    @ApiOperation("编辑用户信息")
    public Result edit(@RequestBody UpdateUserDTO updateUserDTO) {
        log.info("用户信息更新:{}", updateUserDTO);
        return userService.updateUser(updateUserDTO);
    }

}
