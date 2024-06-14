package com.practice.spring_java.domain.recruit.exception;

import com.practice.spring_java.global.exception.ApplicationException;
import com.practice.spring_java.global.exception.ErrorCode;

public class NonExistRecruitPaperException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.NON_EXIST_RECRUIT_PAPER_EXCEPTION;

    public NonExistRecruitPaperException() {

        super(ERROR_CODE);
    }
}
