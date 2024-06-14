package com.practice.spring_java.global.util;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomResponseUtil {

    private static final Logger log = LoggerFactory.getLogger(CustomResponseUtil.class);

    public static void failAuthentication(HttpServletResponse response, String msg, HttpStatus httpStatus) {
        try {
            response.setStatus(httpStatus.value());

            ResponseDTO<Void> errorResponse = ResponseDTO.errorWithMessage(
                    httpStatus, msg);
            SendJsonResponse.sendJsonResponse(response, errorResponse, httpStatus);
        } catch (Exception e) {
            log.error("서버 파싱 에러");
        }
    }
}
