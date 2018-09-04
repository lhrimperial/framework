package com.github.framework.server.cache.exception;



/**
 * @author longhairen
 * @create 2017-05-21 14:35
 * @description key不存在
 **/
public class KeyIsNotFoundException extends RedisCacheStorageException {

    private static final long serialVersionUID = 5165307445946057734L;
    
    public KeyIsNotFoundException(String message) {
        super(message);
    }

    public KeyIsNotFoundException(Throwable e) {
        super(e);
    }

    public KeyIsNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
