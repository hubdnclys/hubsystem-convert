package com.practice.spring_java.global.exception;

public class UserNotFoundException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.USER_NOT_FOUND;

    public UserNotFoundException() {

        super(ERROR_CODE);
    }
}
