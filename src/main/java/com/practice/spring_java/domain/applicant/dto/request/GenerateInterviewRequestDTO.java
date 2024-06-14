package com.practice.spring_java.domain.applicant.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.practice.spring_java.domain.applicant.entity.Applicant;
import com.practice.spring_java.domain.applicant.entity.ApplicantInterview;
import com.practice.spring_java.domain.applicant.enums.InterviewRequestStatus;
import com.practice.spring_java.domain.employee.entity.Employee;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record GenerateInterviewRequestDTO(

        @NotNull(message = "interviewerId 은 필수값입니다")
        long interviewerId,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        @NotNull(message = "interviewDate 은 필수값입니다")
        LocalDateTime interviewDate,
        @NotNull(message = "InterviewRequestStatus 은 필수값입니다")
        InterviewRequestStatus interviewRequestStatus
) {

    public ApplicantInterview toEntity(Applicant applicant, Employee employee) {
        return ApplicantInterview.builder()
                .employee(employee)
                .applicant(applicant)
                .interviewDate(interviewDate)
                .requestChange(interviewRequestStatus)
                .build();
    }
}
