package com.practice.spring_java.domain.recruit.dto.response;


public record RegisterRecruitPaperResponseDTO(

        long recruitStepIdx
) {

    public static RegisterRecruitPaperResponseDTO fromEntity(long id) {
        return new RegisterRecruitPaperResponseDTO(id);
    }
}
