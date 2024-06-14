package com.practice.spring_java.global.exception;

public class ExpiredRefreshTokenException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.EXPIRED_REFRESH_TOKEN_EXCEPTION;

    public ExpiredRefreshTokenException() {

        super(ERROR_CODE);
    }
}
