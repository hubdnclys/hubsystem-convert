package com.practice.spring_java.global.util;

import java.util.regex.Pattern;

public class RegexValidator {
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String KOREAN_NAME_REGEX = "^[가-힣]{2,5}$"; // 한글 2~5글자
    public static final String PASSWORD_REGEX = "^(?=.*[!@#$%^&*()-_+=])[a-zA-Z0-9!@#$%^&*()-_+=]{7,}$"; //특수문자가 들어간 7자 이상의 비밀번호
    public static final String PHONE_REGEX = "^\\d{11}$";

    public static final Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
    public static final Pattern namePattern = Pattern.compile(KOREAN_NAME_REGEX);
    public static final Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);
    public static final Pattern phonePattern = Pattern.compile(PHONE_REGEX);

    public static boolean isValidEmail(String email) {
        return emailPattern.matcher(email).matches();
    }

    public static boolean isValidName(String name) {
        return namePattern.matcher(name).matches();
    }

    public static boolean isValidPassword(String password) {
        return passwordPattern.matcher(password).matches();
    }

    public static boolean isValidPhone(String phone) {
        return phonePattern.matcher(phone).matches();
    }
}