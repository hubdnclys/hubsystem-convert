package com.practice.spring_java.domain.employee.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateEmployeeInfoRequestDTO(

        @NotNull(message = "image은 필수값입니다")
        String image,

        @NotNull(message = "password는 필수값입니다")
        String password,

        @NotNull(message = "phone은 필수값입니다")
        String phone
) {
}
