package com.github.framework.server.cache.exception.security;


import com.github.framework.server.exception.GeneralException;

/**
* @ClassName: FunctionNotValidException
* @Description: 功能无效异常
* @author 龙海仁
* @date 2016年4月21日 下午6:53:47
*
*/

	
public class FunctionNotValidException extends GeneralException {

	private static final long serialVersionUID = 1L;

	public static final String ERROR_CODE = "ERROR.SECURITY.NOTVALID";

	public static final String MESSAGE = "Function is not valid";

	public FunctionNotValidException() {
		super(MESSAGE);
		super.errCode = ERROR_CODE;
	}

	public FunctionNotValidException(String message, Throwable cause) {
		super(message, cause);
		super.errCode = ERROR_CODE;
	}

	public FunctionNotValidException(String msg) {
		super(msg);
		super.errCode = ERROR_CODE;
	}

	public FunctionNotValidException(Throwable cause) {
		super(cause);
		super.errCode = ERROR_CODE;
	}

}
