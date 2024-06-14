package com.practice.spring_java.domain.recruit.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RecruitDepartment {
    PROJECT_MANAGE("PM"),
    PROGRAMMING("개발"),
    DESIGN("디자인"),
    PEOPLE("인사팀");

    private final String description;
}
