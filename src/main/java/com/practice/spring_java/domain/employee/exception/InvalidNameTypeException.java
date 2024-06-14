package com.practice.spring_java.domain.employee.exception;

import com.practice.spring_java.global.exception.ApplicationException;
import com.practice.spring_java.global.exception.ErrorCode;

public class InvalidNameTypeException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.INVALID_NAME_TYPE;

    public InvalidNameTypeException() {

        super(ERROR_CODE);
    }
}
