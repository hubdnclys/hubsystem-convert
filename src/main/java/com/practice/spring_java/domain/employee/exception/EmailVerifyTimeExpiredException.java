package com.practice.spring_java.domain.employee.exception;

import com.practice.spring_java.global.exception.ApplicationException;
import com.practice.spring_java.global.exception.ErrorCode;

public class EmailVerifyTimeExpiredException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.EMAIL_VERIFY_TIME_EXPIRED;

    public EmailVerifyTimeExpiredException() {

        super(ERROR_CODE);
    }
}
