package com.practice.spring_java.domain.recruit.dto.request;

import com.practice.spring_java.domain.recruit.entity.RecruitBenefits;
import com.practice.spring_java.domain.recruit.entity.UploadFile;

public record RegisterUploadFileRequestDTO(

        String originName,
        String saveName,
        String path,
        String extension,
        int size
) {
    public UploadFile toEntity(RecruitBenefits recruitBenefits) {
        return UploadFile.builder()
                .originName(originName)
                .saveName(saveName)
                .path(path)
                .extension(extension)
                .size(size)
                .recruitBenefits(recruitBenefits)
                .build();
    }
}