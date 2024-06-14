package com.practice.spring_java.domain.recruit.dto.response;


import com.practice.spring_java.domain.recruit.entity.RecruitStep;

public record GetRecruitStepListResponseDTO(

        String title,
        boolean isAlarm,
        boolean isRejectAlarm,
        long recruitPaperNo,
        long alarmTemplateNo,
        long rejectTemplateNo,
        long testTemplateNo
) {

    public static GetRecruitStepListResponseDTO fromEntity(
            RecruitStep recruitStep) {

        return new GetRecruitStepListResponseDTO(
                recruitStep.getTitle(),
                recruitStep.isAlarm(),
                recruitStep.isRejectAlarm(),
                recruitStep.getRecruitPaperNo(),
                recruitStep.getAlarmTemplateNo(),
                recruitStep.getRejectTemplateNo(),
                recruitStep.getTestTemplateNo()
        );
    }
}
