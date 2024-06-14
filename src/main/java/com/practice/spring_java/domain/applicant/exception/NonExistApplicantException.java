package com.practice.spring_java.domain.applicant.exception;

import com.practice.spring_java.global.exception.ApplicationException;
import com.practice.spring_java.global.exception.ErrorCode;

public class NonExistApplicantException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.NON_EXIST_APPLICANT_EXCEPTION;

    public NonExistApplicantException() {

        super(ERROR_CODE);
    }
}
