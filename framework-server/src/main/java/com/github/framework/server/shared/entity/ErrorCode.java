package com.github.framework.server.shared.entity;

/**
 * @author longhr
 * @version 2017/11/8 0008
 */
public enum ErrorCode {

    SUCCESS("100000","成功！"),
    FAILURE("100001","系统异常！");

    private String code;
    private String name;

    ErrorCode(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }
    public String getName() {
        return name;
    }
}
