package com.practice.spring_java.domain.employee.controller;

import com.practice.spring_java.domain.employee.exception.*;
import com.practice.spring_java.global.exception.UserNotFoundException;
import com.practice.spring_java.global.util.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeControllerAdvice {

    @ExceptionHandler(value = {
            EmailDuplicateException.class,
            InvalidEmailException.class,
            InvalidNameException.class,
            InvalidPassWordException.class,
            PermissionDeniedException.class,
            InvalidNameTypeException.class,
            UserNotFoundException.class,
    })
    public ResponseEntity<ResponseDTO<Void>> handleEmployeeControllerExceptions(
            RuntimeException exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception instanceof EmailDuplicateException ||
                exception instanceof InvalidEmailException ||
                exception instanceof InvalidNameException ||
                exception instanceof InvalidPassWordException ||
                exception instanceof PermissionDeniedException ||
                exception instanceof InvalidNameTypeException ||
                exception instanceof UserNotFoundException) {
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(
                ResponseDTO.errorWithMessage(status, exception.getMessage()));
    }
}
