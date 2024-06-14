package com.practice.spring_java.domain.employee.exception;

import com.practice.spring_java.global.exception.ApplicationException;
import com.practice.spring_java.global.exception.ErrorCode;

public class InvalidPassWordException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.INVALID_PASSWORD_TYPE;

    public InvalidPassWordException() {

        super(ERROR_CODE);
    }
}
