package com.practice.spring_java.domain.recruit.exception;

import com.practice.spring_java.global.exception.ApplicationException;
import com.practice.spring_java.global.exception.ErrorCode;

public class NonExistTemplateException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.NON_EXIST_TEMPLATE_EXCEPTION;

    public NonExistTemplateException() {

        super(ERROR_CODE);
    }
}
