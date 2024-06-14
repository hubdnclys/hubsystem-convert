package com.practice.spring_java.domain.applicant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RecruitmentStage {
    APPLICATION_SUBMISSION("지원서 접수"),
    FINAL_ACCEPTANCE("최종 합격"),
    INTERVIEW("인터뷰"),
    TEST("테스트"),
    OTHER("기타"),
    FINAL_REJECTION("불합격");

    private final String description;
}
//채용단계
