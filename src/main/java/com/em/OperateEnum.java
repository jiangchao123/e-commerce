package com.em;

/**
 * Created by jiangchao08 on 17/2/2.
 */
public enum OperateEnum {

    ADD("新增"), UPDATE("修改");

    private String code;

    OperateEnum(String code) {
        this.code = code;
    }

    public String code() {
        return code;
    }
}
