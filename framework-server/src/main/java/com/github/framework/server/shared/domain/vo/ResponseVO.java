package com.github.framework.server.shared.domain.vo;

import com.github.framework.server.shared.entity.IToken;

import java.io.Serializable;


/**
 * @author longhr
 * @version 2017/11/8 0008
 */
public class ResponseVO<T> implements Serializable{
    private static final long serialVersionUID = -3640707473825917708L;
    private boolean success;
    private String code;
    private String message;
    private String token;
    private T result;

    public ResponseVO(){}

    public ResponseVO(boolean success) {
        this.success = success;
    }

    public ResponseVO(boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}


