package com.practice.spring_java.domain.recruit.entity;

import com.practice.spring_java.domain.recruit.enums.RecruitPaperType;
import com.practice.spring_java.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//테스트/질문지
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class ApplicantQuestionSheet extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "applicant_question_id")
    private long id;

    private long applicantId;
    private long recruitStepNo; //recruit step 번호
    private long recruitPaperIdx; //테스트/질문지 idx
    private long recruitQuestionIdx; //테스트/질문지 문항 고유번호
    private RecruitPaperType testType; //어떤 타입 인지
    private long registerEmployeeId;
    private long lastModifyEmployeeId;

    @Builder
    ApplicantQuestionSheet(long applicantId, long recruitStepNo, long recruitPaperIdx, long recruitQuestionIdx
            , RecruitPaperType testType, Long registerEmployeeId, Long lastModifyEmployeeId) {
        this.applicantId = applicantId;
        this.recruitStepNo = recruitStepNo;
        this.recruitPaperIdx = recruitPaperIdx;
        this.recruitQuestionIdx = recruitQuestionIdx;
        this.testType = testType;
        this.registerEmployeeId = registerEmployeeId;
        this.lastModifyEmployeeId = lastModifyEmployeeId;
    }
}