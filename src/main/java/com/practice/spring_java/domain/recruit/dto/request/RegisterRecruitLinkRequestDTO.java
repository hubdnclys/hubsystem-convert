package com.practice.spring_java.domain.recruit.dto.request;

import com.practice.spring_java.domain.recruit.entity.Recruit;
import com.practice.spring_java.domain.recruit.entity.RecruitLink;

public record RegisterRecruitLinkRequestDTO(
        String siteName,
        String siteLink
) {
    public RecruitLink toEntity(Recruit recruit) {
        return RecruitLink.builder()
                .siteName(siteName)
                .siteLink(siteLink)
                .recruit(recruit)
                .build();
    }
}