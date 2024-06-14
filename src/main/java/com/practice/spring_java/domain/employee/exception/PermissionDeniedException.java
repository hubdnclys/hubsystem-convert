package com.practice.spring_java.domain.employee.exception;

import com.practice.spring_java.global.exception.ApplicationException;
import com.practice.spring_java.global.exception.ErrorCode;

public class PermissionDeniedException extends ApplicationException {

    private static final ErrorCode ERROR_CODE = ErrorCode.PERMISSION_DENIED;

    public PermissionDeniedException() {

        super(ERROR_CODE);
    }
}
