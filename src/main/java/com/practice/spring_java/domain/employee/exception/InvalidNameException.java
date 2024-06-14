package com.practice.spring_java.domain.employee.exception;

import com.practice.spring_java.global.exception.ApplicationException;
import com.practice.spring_java.global.exception.ErrorCode;

public class InvalidNameException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.INVALID_NAME;

    public InvalidNameException() {

        super(ERROR_CODE);
    }
}
