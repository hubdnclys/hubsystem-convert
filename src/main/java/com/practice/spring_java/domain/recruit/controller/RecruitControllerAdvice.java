package com.practice.spring_java.domain.recruit.controller;

import com.practice.spring_java.domain.employee.exception.EmailVerifyTimeExpiredException;
import com.practice.spring_java.domain.recruit.exception.NonExistRecruitPaperException;
import com.practice.spring_java.domain.recruit.exception.NonExistTemplateException;
import com.practice.spring_java.global.util.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RecruitControllerAdvice {

    @ExceptionHandler(value = {
            EmailVerifyTimeExpiredException.class,
            NonExistRecruitPaperException.class,
            NonExistTemplateException.class
    })
    public ResponseEntity<ResponseDTO<Void>> handleRecruitControllerExceptions(
            RuntimeException exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception instanceof EmailVerifyTimeExpiredException
                || exception instanceof NonExistRecruitPaperException
                || exception instanceof NonExistTemplateException) {
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(
                ResponseDTO.errorWithMessage(status, exception.getMessage()));
    }
}
