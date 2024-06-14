package com.practice.spring_java.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // Permission
    PERMISSION_DENIED(HttpStatus.FORBIDDEN, "해당 작업을 수행할 권한이 업습니다."),

    // USER
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다."),
    USER_ALREADY_REGISTERED(HttpStatus.BAD_REQUEST, "이미 가입된 회원입니다."),
    USER_INVALID_ACCESS(HttpStatus.BAD_REQUEST, "잘못된 유저의 접근입니다."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "잘못된 이메일 형식"),
    INVALID_NAME(HttpStatus.BAD_REQUEST, "존재하지 않거나 잘못된 이름 입니다."),
    INVALID_NAME_TYPE(HttpStatus.BAD_REQUEST, "잘못된 이름 형식 입니다."),
    INVALID_PHONE_TYPE(HttpStatus.BAD_REQUEST, "잘못된 전화번호 형식 입니다."),
    INVALID_PASSWORD_TYPE(HttpStatus.BAD_REQUEST, "잘못된 비밀번호 형식, 7자의 이상이고 특수문자가 하나 들어가야 합니다."),
    EMAIL_VERIFY_TIME_EXPIRED(HttpStatus.BAD_REQUEST, "이메일 인증 시간이 초과되었습니다."),

    // AUTH
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다."),

    // RECRUIT
    NON_EXIST_RECRUIT_PAPER_EXCEPTION(HttpStatus.BAD_REQUEST, "존재하지않는 recruit paper입니다."),
    NON_EXIST_RECRUIT_EXCEPTION(HttpStatus.BAD_REQUEST, "존재하지않는 recruit 입니다."),
    NON_EXIST_TEMPLATE_EXCEPTION(HttpStatus.BAD_REQUEST, "존재하지않는 template 입니다."),

    // APPLICANT
    NON_EXIST_APPLICANT_EXCEPTION(HttpStatus.BAD_REQUEST, "존재하지않는 applicant 입니다."),

    // TOKEN
    EXPIRED_REFRESH_TOKEN_EXCEPTION(HttpStatus.NOT_FOUND, "만료된 refresh token 입니다."),

    // 5xx
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 에러");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
