package com.practice.spring_java.domain.employee.exception;

import com.practice.spring_java.global.exception.ApplicationException;
import com.practice.spring_java.global.exception.ErrorCode;

public class InvalidPhoneTypeException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.INVALID_PHONE_TYPE;

    public InvalidPhoneTypeException() {

        super(ERROR_CODE);
    }
}
