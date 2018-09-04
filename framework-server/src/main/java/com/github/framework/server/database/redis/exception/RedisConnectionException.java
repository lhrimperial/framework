package com.github.framework.server.database.redis.exception;


import com.github.framework.server.exception.GeneralException;

/**
 * @author longhairen
 * @create 2017-05-21 14:35
 * @description
 **/
public class RedisConnectionException extends GeneralException {

    private static final long serialVersionUID = -4269004402633873780L;

    public RedisConnectionException(String message) {
        super(message);
    }

    public RedisConnectionException(Throwable e) {
        super(e);
    }

    public RedisConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
