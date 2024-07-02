package com.elm.entity;

public enum FileStatus {
    DESTORY("硬删除"),
    DELETE("软删除"),
    HIDE("隐藏"),
    NORMAL("正常");

    private final String displayName;

    private FileStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
