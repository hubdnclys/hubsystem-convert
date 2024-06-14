package com.practice.spring_java.domain.recruit.entity;

import com.practice.spring_java.domain.recruit.enums.RecruitPaperType;
import com.practice.spring_java.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//테스트 또는 질문지
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecruitPaper extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_paper_id")
    private long id;

    private String title;
    private RecruitPaperType testType;
    private long registerEmployeeId;
    private long lastModifyEmployeeId;

    @OneToMany(mappedBy = "recruitPaper", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<RecruitPaperQuestion> recruitPaperQuestionList = new ArrayList<>();

    @Builder
    RecruitPaper(String title, RecruitPaperType testType, long registerEmployeeId, long lastModifyEmployeeId) {
        this.title = title;
        this.testType = testType;
        this.registerEmployeeId = registerEmployeeId;
        this.lastModifyEmployeeId = lastModifyEmployeeId;
    }
}