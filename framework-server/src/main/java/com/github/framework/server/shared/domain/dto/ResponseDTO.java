package com.github.framework.server.shared.domain.dto;

import java.io.Serializable;

/**
 *
 */
public class ResponseDTO<T> implements Serializable {
    private static final long serialVersionUID = -4916725144793815582L;
    private boolean success;
    private String resCode;
    private String resMsg;
    private T result;

    public ResponseDTO(){}

    public ResponseDTO(boolean success) {
        this.success = success;
    }

    public ResponseDTO(boolean success, String resCode, String resMsg) {
        this.success = success;
        this.resCode = resCode;
        this.resMsg = resMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
