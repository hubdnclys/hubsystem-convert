package com.practice.spring_java.domain.employee.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EmployeeRank {
    CEO("대표"),
    MANAGER("실장"),
    DIRECTOR("부정"),
    SECTION_CHIEF("과장"),
    STAFF("사원");

    private final String description;
}
