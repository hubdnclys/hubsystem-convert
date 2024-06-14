package com.practice.spring_java.domain.recruit.dto.request;

import com.practice.spring_java.domain.recruit.entity.RecruitPaper;
import com.practice.spring_java.domain.recruit.entity.RecruitPaperQuestion;

//전형 질문 상세 request
public record RegisterRecruitPaperQuestionRequestDTO(

        String question,    //질문 제목
        String questionContent, //질문 내용
        String answer //정답

) {
    public RecruitPaperQuestion toEntity(RecruitPaper recruitPaper) {
        return RecruitPaperQuestion.builder()
                .question(question)
                .questionContent(questionContent)
                .answer(answer)
                .recruitPaper(recruitPaper)
                .build();
    }
}