package com.elm.entity;

public enum UserStatus {
    OFF("离线"),
    ON("在线");

    private final String displayName;

    private UserStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
