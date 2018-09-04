package com.github.framework.server.web.xss;


import com.github.framework.server.exception.GeneralException;

public class ParametersValidatorException extends GeneralException {

	private static final long serialVersionUID = -7792624164333267395L;

	public ParametersValidatorException(String message) {
        super(message);
    }

    public ParametersValidatorException(Throwable e) {
        super(e);
    }

    public ParametersValidatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
