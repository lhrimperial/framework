package com.github.framework.server.cache.exception;


import com.github.framework.server.exception.GeneralException;

/**
* @ClassName: RedisCacheStorageException
* @Description: 查询参数异常
* @author longhairen
* @date 2014年4月22日 下午1:26:36
*
*/
public class RedisCacheStorageException extends GeneralException {

    private static final long serialVersionUID = 4664623827741256267L;
    
    public RedisCacheStorageException(String message) {
        super(message);
    }

    public RedisCacheStorageException(Throwable e) {
        super(e);
    }

    public RedisCacheStorageException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
