package com.elm.vo;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
@ApiOperation("用户登录返回值")
public class UserLoginVO {
    private String username;
    private String id;
    private String token;
}
