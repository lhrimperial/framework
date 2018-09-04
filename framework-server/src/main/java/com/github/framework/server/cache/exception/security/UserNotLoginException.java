package com.github.framework.server.cache.exception.security;


import com.github.framework.server.exception.GeneralException;

/**
* @ClassName: UserNotLoginException
* @Description: 用户未登录异常
* @author 龙海仁
* @date 2016年4月21日 下午6:54:06
*
*/

	
public class UserNotLoginException extends GeneralException {

	private static final long serialVersionUID = -8447576671797891073L;

	public static final String ERROR_CODE = "ERROR.SECURITY.USERNOTLOGIN";

	public static final String MESSAGE = "User not logged in";

	public UserNotLoginException() {
		super(MESSAGE);
		super.errCode = ERROR_CODE;
	}

	public UserNotLoginException(String message, Throwable cause) {
		super(message, cause);
		super.errCode = ERROR_CODE;
	}

	public UserNotLoginException(String msg) {
		super(msg);
		super.errCode = ERROR_CODE;
	}

	public UserNotLoginException(Throwable cause) {
		super(cause);
		super.errCode = ERROR_CODE;
	}

}
