package com.practice.spring_java.domain.employee.dto.request;

import jakarta.validation.constraints.NotNull;

public record VerifyEmailRequestDTO(
        @NotNull(message = "email은 필수값입니다")
        String email
) {
}
