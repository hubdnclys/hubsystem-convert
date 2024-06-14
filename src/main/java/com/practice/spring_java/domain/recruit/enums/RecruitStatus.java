package com.practice.spring_java.domain.recruit.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RecruitStatus {
    TEMPORARY_STORAGE("임시저장"),
    SCHEDULED_HIRING("채용예정"),
    HIRING_IN_PROGRESS("채용중"),
    HIRING_COMPLETED("채용종료"),
    PRIVATE("비공개");

    private final String description;
}
