package com.elm.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AccountPageQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String accountName;
    private Integer page;
    private Integer pageSize;
}
