package com.practice.spring_java.domain.recruit.dto.request;

import com.practice.spring_java.domain.recruit.entity.Recruit;
import com.practice.spring_java.domain.recruit.entity.RecruitBenefits;

import java.util.List;

public record RegisterRecruitBenefitsRequestDTO(

        String icon,
        String title, //혜택 복지명
        String description, // 추가설명
        List<RegisterUploadFileRequestDTO> recruitBenefits

) {
    public RecruitBenefits toEntity(Recruit recruit) {
        return RecruitBenefits.builder()
                .icon(icon)
                .title(title)
                .description(description)
                .isActive(true)
                .isDeleted(false)
                .recruit(recruit)
                .build();
    }
}