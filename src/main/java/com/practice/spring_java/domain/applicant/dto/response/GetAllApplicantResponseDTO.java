package com.practice.spring_java.domain.applicant.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.practice.spring_java.domain.applicant.entity.Applicant;
import com.practice.spring_java.domain.applicant.enums.RecruitmentStage;

import java.time.LocalDateTime;

public record GetAllApplicantResponseDTO(

        long applicantId,
        RecruitmentStage recruitmentStage, //채용 단계
        String recruitName, // 지원한공고 이름
        String name,
        boolean isNew, //신입 여부
        int experienceYear, //경력 년차
        String workspaceAddress, //근무지
        String phone,
        String email,
        String applicationPath, //지원경로

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime modifiedAt, //마지막 변경 일
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime submittedAt //지원일
) {
    public static GetAllApplicantResponseDTO fromEntity(
            Applicant applicant) {

        return new GetAllApplicantResponseDTO(
                applicant.getId(),
                applicant.getRecruitmentStage(),
                applicant.getRecruit().getTitle(),
                applicant.getName(),
                applicant.isNew(),
                applicant.getExperienceYear(),
                applicant.getRecruit().getAddress(),
                applicant.getPhone(),
                applicant.getEmail(),
                applicant.getApplicationPath(),
                applicant.getUpdatedAt(),
                applicant.getCreatedAt()
        );
    }
}
