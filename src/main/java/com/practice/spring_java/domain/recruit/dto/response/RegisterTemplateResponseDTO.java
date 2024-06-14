package com.practice.spring_java.domain.recruit.dto.response;


public record RegisterTemplateResponseDTO(

        long templateIdx
) {

    public static RegisterTemplateResponseDTO fromEntity(long id) {

        return new RegisterTemplateResponseDTO(id);
    }
}
