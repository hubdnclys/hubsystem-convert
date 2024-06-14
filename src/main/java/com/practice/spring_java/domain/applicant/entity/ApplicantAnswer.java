package com.practice.spring_java.domain.applicant.entity;

import com.practice.spring_java.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//지원자의 질문
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ApplicantAnswer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_answer_id")
    private Long id;

    private long recruitPaperQuestionId; //질문지 id

    private String answer; //지원자의 응답

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @Builder
    private ApplicantAnswer(
            long recruitPaperQuestionId, String answer, Applicant applicant
    ) {
        this.recruitPaperQuestionId = recruitPaperQuestionId;
        this.answer = answer;
        this.applicant = applicant;
    }
}