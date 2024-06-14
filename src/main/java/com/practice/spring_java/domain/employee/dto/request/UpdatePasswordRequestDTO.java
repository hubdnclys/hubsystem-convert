package com.practice.spring_java.domain.employee.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdatePasswordRequestDTO(
        @NotNull(message = "password은 필수값입니다")
        String password,

        @NotNull(message = "name은 필수값입니다")
        String name,

        @NotNull(message = "email은 필수값입니다")
        String email
) {
}
