package com.github.framework.server.cache.exception;


import com.github.framework.server.exception.GeneralException;

/**
* @ClassName: CacheConfigException
* @Description: 缓存配置异常
* @author longhairen
* @date 2017年4月22日 下午1:30:00
*
*/
public class CacheConfigException extends GeneralException {

	private static final long serialVersionUID = 437438995471412241L;

	public CacheConfigException(String msg) {
        super(msg);
    }
    
}
