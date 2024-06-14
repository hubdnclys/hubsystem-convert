package com.practice.spring_java.domain.applicant.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.practice.spring_java.domain.applicant.entity.Applicant;
import com.practice.spring_java.domain.applicant.entity.ApplicantInterview;

import java.time.LocalDateTime;

public record GetInterviewLogResponseDTO(

        String interviewer,
        String applicantName,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime requestTime,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        LocalDateTime interviewTime
) {
    public static GetInterviewLogResponseDTO fromEntity(Applicant applicant, ApplicantInterview applicantInterview) {

        return new GetInterviewLogResponseDTO(
                applicantInterview.getEmployee().getName(),
                applicant.getName(),
                applicantInterview.getCreatedAt(),
                applicantInterview.getInterviewDate()
        );
    }
}
