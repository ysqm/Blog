package com.elm.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;
    private Integer templateId;
}