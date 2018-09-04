package com.github.framework.server.cache.exception;



/**
* @ClassName: ValueIsBlankException
* @Description: key存在，value为空
* @author longhairen
* @date 2017年4月22日 下午1:26:53
*
*/
public class ValueIsBlankException extends RedisCacheStorageException {

    private static final long serialVersionUID = 5536157410092139146L;
    
    public ValueIsBlankException(String message) {
        super(message);
    }

    public ValueIsBlankException(Throwable e) {
        super(e);
    }

    public ValueIsBlankException(String message, Throwable cause) {
        super(message, cause);
    }

}
