package com.practice.spring_java.domain.recruit.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EmploymentType {
    REGULAR("정규직"),
    CONTRACT("계약직"),
    FREELANCER("프리랜서");

    private final String description;
}
