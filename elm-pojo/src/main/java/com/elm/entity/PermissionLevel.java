package com.elm.entity;

public enum PermissionLevel {
    BANNED("封禁"),
    USER("用户"),
    ADMIN("管理员"),
    SUPER_ADMIN("超级管理员");

    private final String displayName;

    private PermissionLevel(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
