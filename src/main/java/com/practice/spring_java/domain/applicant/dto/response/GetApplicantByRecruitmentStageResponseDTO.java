package com.practice.spring_java.domain.applicant.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.practice.spring_java.domain.applicant.entity.Applicant;

import java.time.LocalDateTime;

public record GetApplicantByRecruitmentStageResponseDTO(

        long applicantId,
        String name,
        boolean isNew, //신입 여부
        int experienceYear, //경력 년차
        String phone,
        String email,
        String applicationPath, //지원경로

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime submittedAt //지원일
) {
    public static GetApplicantByRecruitmentStageResponseDTO fromEntity(
            Applicant applicant) {

        return new GetApplicantByRecruitmentStageResponseDTO(
                applicant.getId(),
                applicant.getName(),
                applicant.isNew(),
                applicant.getExperienceYear(),
                applicant.getPhone(),
                applicant.getEmail(),
                applicant.getApplicationPath(),
                applicant.getCreatedAt()
        );
    }
}
