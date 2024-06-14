package com.practice.spring_java.domain.applicant.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.practice.spring_java.domain.applicant.entity.ApplicantInterview;

import java.time.LocalDateTime;

public record GetInterviewScheduleResponseDTO(

        String interviewer,
        String applicantName,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime interviewTime
) {
    public static GetInterviewScheduleResponseDTO fromEntity(
            ApplicantInterview applicantInterview) {

        return new GetInterviewScheduleResponseDTO(
                applicantInterview.getEmployee().getName(),
                applicantInterview.getApplicant().getName(),
                applicantInterview.getInterviewDate()
        );
    }
}
