package com.practice.spring_java.domain.applicant.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.practice.spring_java.domain.applicant.entity.Applicant;
import com.practice.spring_java.domain.applicant.enums.RecruitmentStage;

import java.time.LocalDateTime;
import java.util.List;

public record GetApplicantDetailResponseDTO(

        long applicantId,
        RecruitmentStage recruitmentStage, //채용 단계
        String name,
        boolean isNew, //신입 여부
        int experienceYear, //경력 년차
        String phone,
        String email,

        List<GetApplicantQuestionAnswerResponseDTO> applicantQuestionAnswerList,

        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime modifiedAt, //마지막 변경 일
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDateTime submittedAt //지원일
) {
    public static GetApplicantDetailResponseDTO fromEntity(
            Applicant applicant,
            List<GetApplicantQuestionAnswerResponseDTO> applicantQuestionAnswerList) {

        return new GetApplicantDetailResponseDTO(
                applicant.getId(),
                applicant.getRecruitmentStage(),
                applicant.getName(),
                applicant.isNew(),
                applicant.getExperienceYear(),
                applicant.getPhone(),
                applicant.getEmail(),
                applicantQuestionAnswerList,
                applicant.getUpdatedAt(),
                applicant.getCreatedAt()
        );
    }
}
