package com.practice.spring_java.domain.employee.exception;

import com.practice.spring_java.global.exception.ApplicationException;
import com.practice.spring_java.global.exception.ErrorCode;

public class InvalidEmailException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.INVALID_EMAIL;

    public InvalidEmailException() {

        super(ERROR_CODE);
    }
}
