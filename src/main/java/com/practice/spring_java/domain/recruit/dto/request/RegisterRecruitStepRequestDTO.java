package com.practice.spring_java.domain.recruit.dto.request;

import com.practice.spring_java.domain.recruit.entity.Recruit;
import com.practice.spring_java.domain.recruit.entity.RecruitStep;

public record RegisterRecruitStepRequestDTO(

        String title,
        boolean isAlarm, //알림 여부
        boolean isRejectAlarm, // 불합격 알림 여부
        long recruitPaperNo,
        long alarmTemplateNo, // 합격 메일 템플릿 idx
        long rejectTemplateNo // 불합격 메일 템플릿 idx

) {
    public RecruitStep toEntity(Recruit recruit) {
        return RecruitStep.builder()
                .title(title)
                .isAlarm(isAlarm)
                .isRejectAlarm(isRejectAlarm)
                .recruitPaperNo(recruitPaperNo)
                .alarmTemplateNo(alarmTemplateNo)
                .rejectTemplateNo(rejectTemplateNo)
                .isDeleted(false)
                .recruit(recruit)
                .build();
    }
}
