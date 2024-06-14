package com.practice.spring_java.domain.recruit.dto.response;


import com.practice.spring_java.domain.recruit.entity.RecruitLink;

public record GetRecruitLinkListResponseDTO(

        String siteName,
        String siteLink,
        int wantedCount
) {

    public static GetRecruitLinkListResponseDTO fromEntity(RecruitLink recruitLink) {

        return new GetRecruitLinkListResponseDTO(
                recruitLink.getSiteName(),
                recruitLink.getSiteLink(),
                recruitLink.getWantedCount()
        );
    }
}
