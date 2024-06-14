package com.practice.spring_java.domain.employee.controller;

import com.practice.spring_java.domain.employee.exception.EmailVerifyTimeExpiredException;
import com.practice.spring_java.global.util.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MailSendControllerAdvice {

    @ExceptionHandler(value = {
            EmailVerifyTimeExpiredException.class
    })
    public ResponseEntity<ResponseDTO<Void>> handleMailSendControllerExceptions(
            RuntimeException exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception instanceof EmailVerifyTimeExpiredException) {
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(
                ResponseDTO.errorWithMessage(status, exception.getMessage()));
    }
}
