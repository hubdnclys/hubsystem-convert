package com.practice.spring_java.domain.recruit.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum RecruitPaperType {
    CODING_TEST("코딩 테스트"),
    INTERVIEW("인터뷰"),
    ETC("기타");

    private final String description;
}
