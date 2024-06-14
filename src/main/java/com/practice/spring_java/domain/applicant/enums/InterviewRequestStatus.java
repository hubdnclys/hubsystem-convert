package com.practice.spring_java.domain.applicant.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum InterviewRequestStatus {
    INTERVIEW_REQUESTED("인터뷰 요청"),
    INTERVIEW_CHANGE_REQUESTED("인터뷰 변경 요청"),
    INTERVIEW_CONFIRMATION_REQUESTED("인터뷰 확정");

    private final String description;
}
