package com.practice.spring_java.domain.recruit.entity;

import com.practice.spring_java.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//테스트 또는 질문지 -> 질문지 상세
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecruitPaperQuestion extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_paper_question_id")
    private long id;

    private String question;

    private String questionContent;

    private String answer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recruit_paper_id")
    private RecruitPaper recruitPaper;

    @Builder
    public RecruitPaperQuestion(
            String question, String questionContent, String answer, RecruitPaper recruitPaper) {
        this.question = question;
        this.questionContent = questionContent;
        this.answer = answer;
        this.recruitPaper = recruitPaper;
    }
}