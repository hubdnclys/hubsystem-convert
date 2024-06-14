package com.practice.spring_java.domain.applicant.dto.response;


import com.practice.spring_java.domain.applicant.enums.RecruitmentStage;

import java.util.List;

public record GetAllApplicantByRecruitmentStageResponseDTO(

        RecruitmentStage recruitmentStage, //채용 단계
        List<GetApplicantByRecruitmentStageResponseDTO> applicants

) {
    public static GetAllApplicantByRecruitmentStageResponseDTO fromEntity(
            RecruitmentStage recruitmentStage, List<GetApplicantByRecruitmentStageResponseDTO> applicants) {

        return new GetAllApplicantByRecruitmentStageResponseDTO(
                recruitmentStage,
                applicants
        );
    }
}
