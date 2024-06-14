package com.practice.spring_java.domain.applicant.controller;

import com.practice.spring_java.domain.applicant.exception.NonExistApplicantException;
import com.practice.spring_java.domain.recruit.exception.NonExistRecruitException;
import com.practice.spring_java.global.util.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicantControllerAdvice {

    @ExceptionHandler(value = {
            NonExistRecruitException.class,
            NonExistApplicantException.class
    })
    public ResponseEntity<ResponseDTO<Void>> handleApplicantControllerExceptions(
            RuntimeException exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception instanceof NonExistRecruitException
                || exception instanceof NonExistApplicantException) {
            status = HttpStatus.BAD_REQUEST;
        }

        return ResponseEntity.status(status).body(
                ResponseDTO.errorWithMessage(status, exception.getMessage()));
    }
}
