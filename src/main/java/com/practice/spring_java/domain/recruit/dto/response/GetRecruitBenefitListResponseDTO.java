package com.practice.spring_java.domain.recruit.dto.response;


import com.practice.spring_java.domain.recruit.entity.RecruitBenefits;

import java.util.List;

public record GetRecruitBenefitListResponseDTO(

        String icon,
        String title,
        String description,
        boolean isActive,
        List<GetUploadFileListResponseDTO> uploadFile
) {

    public static GetRecruitBenefitListResponseDTO fromEntity(
            RecruitBenefits recruitBenefits, List<GetUploadFileListResponseDTO> uploadFile) {

        return new GetRecruitBenefitListResponseDTO(
                recruitBenefits.getIcon(),
                recruitBenefits.getTitle(),
                recruitBenefits.getDescription(),
                recruitBenefits.isActive(),
                uploadFile
        );
    }
}
