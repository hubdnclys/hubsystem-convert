package com.practice.spring_java.domain.recruit.entity;

import com.practice.spring_java.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RecruitStep extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_step_id")
    private long id;

    private String title; //전형 제목
    private boolean isAlarm; //알림 여부
    private boolean isRejectAlarm; // 불합격 알림 여부
    private long recruitPaperNo; //전형 단계 문제 템플릿 idx
    private long alarmTemplateNo; //합격 메일 템플릿 idx
    private long rejectTemplateNo; //불합격 메일 템플릿 idx
    private long testTemplateNo; //테스트 전용 템플릿 idx
    private boolean isDeleted; //삭제 여부

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;

    @Builder
    RecruitStep(String title, boolean isAlarm, boolean isRejectAlarm, long recruitPaperNo,
                long alarmTemplateNo, long rejectTemplateNo, long testTemplateNo, boolean isDeleted,
                Recruit recruit) {

        this.title = title;
        this.isAlarm = isAlarm;
        this.isRejectAlarm = isRejectAlarm;
        this.recruitPaperNo = recruitPaperNo;
        this.alarmTemplateNo = alarmTemplateNo;
        this.rejectTemplateNo = rejectTemplateNo;
        this.testTemplateNo = testTemplateNo;
        this.isDeleted = isDeleted;
        this.recruit = recruit;
    }
}