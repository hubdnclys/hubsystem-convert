package com.practice.spring_java.global.jwt.refresh_token;

import com.practice.spring_java.domain.employee.exception.EmailDuplicateException;
import com.practice.spring_java.global.exception.ExpiredRefreshTokenException;
import com.practice.spring_java.global.exception.UserNotFoundException;
import com.practice.spring_java.global.util.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TokenControllerAdvice {

    @ExceptionHandler(value = {
            ExpiredRefreshTokenException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<ResponseDTO<Void>> handleTokenControllerExceptions(
            RuntimeException exception) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception instanceof EmailDuplicateException ||
                exception instanceof UserNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(status).body(
                ResponseDTO.errorWithMessage(status, exception.getMessage()));
    }
}
