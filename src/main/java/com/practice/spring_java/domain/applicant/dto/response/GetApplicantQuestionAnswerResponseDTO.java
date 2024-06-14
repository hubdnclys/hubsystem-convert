package com.practice.spring_java.domain.applicant.dto.response;

import com.practice.spring_java.domain.applicant.entity.ApplicantAnswer;
import com.practice.spring_java.domain.recruit.entity.RecruitPaperQuestion;

public record GetApplicantQuestionAnswerResponseDTO(

        String questionContent,
        String answer
) {
    public static GetApplicantQuestionAnswerResponseDTO fromEntity(
            ApplicantAnswer applicantAnswer,
            RecruitPaperQuestion recruitPaperQuestion) {

        return new GetApplicantQuestionAnswerResponseDTO(
                recruitPaperQuestion.getQuestionContent(),
                applicantAnswer.getAnswer()
        );
    }
}
