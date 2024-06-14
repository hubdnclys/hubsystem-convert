package com.practice.spring_java.domain.applicant.dto.request;

import jakarta.validation.constraints.NotNull;

public record RegisterApplicantToEmployeeRequestDTO(

        @NotNull(message = "applicantId는 필수 값 입니다.")
        Long applicantId,
        @NotNull(message = "password는 필수 값 입니다.")
        String password
) {
}
