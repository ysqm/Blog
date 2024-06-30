package com.elm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@ApiModel(description = "客户登录时传递的数据模型")
public class GetMessageDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账号&&uid")
    private Integer id;

    @ApiModelProperty("账号名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("email")
    private String email;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("个人介绍")
    private String bio;

    @ApiModelProperty("微信账号")
    private String wechatAccount;

    @ApiModelProperty("QQ账号")
    private String qqAccount;

    @ApiModelProperty("是否登出")
    private Integer isLoggedOut;
}
