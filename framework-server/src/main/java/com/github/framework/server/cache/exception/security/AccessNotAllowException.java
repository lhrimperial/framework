package com.github.framework.server.cache.exception.security;


import com.github.framework.server.exception.GeneralException;

/**
* @ClassName: AccessNotAllowException
* @Description: 访问拒绝异常
* @author 龙海仁
* @date 2016年4月21日 下午6:52:35
*
*/
public class AccessNotAllowException extends GeneralException {

	private static final long serialVersionUID = -5710513168282003818L;
	
	public static final String ERROR_CODE = "ERROR.SECURITY.NOTALLOW";
	
	public static final String MESSAGE = "Method not allow access";
	
	public AccessNotAllowException() {
		this(MESSAGE);
		super.errCode = ERROR_CODE;
	}

	public AccessNotAllowException(String msg) {
		super(msg);
		super.errCode = ERROR_CODE;
	}

	@Override
	public String getErrorCode() {
		return super.errCode;
	}

}
