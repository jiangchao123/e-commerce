package com.em;

/**
 * Created by jiangchao08 on 17/2/2.
 */
public enum UserStatus {

    NORMAL("正常"), DELETED("删除");

    private String code;

    UserStatus (String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
