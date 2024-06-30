package com.elm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private LocalDateTime registerDate;
    private String bio;
    private LocalDateTime updateTime;
    private String wechatAccount;
    private String qqAccount;
    private Integer isLoggedOut;
    private LocalDateTime lastLoginTime;
}