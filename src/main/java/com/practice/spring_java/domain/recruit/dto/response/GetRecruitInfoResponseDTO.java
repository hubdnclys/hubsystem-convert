package com.practice.spring_java.domain.recruit.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.practice.spring_java.domain.recruit.entity.Recruit;
import com.practice.spring_java.domain.recruit.enums.EmploymentType;
import com.practice.spring_java.domain.recruit.enums.RecruitDepartment;

import java.time.LocalDate;
import java.util.List;

public record GetRecruitInfoResponseDTO(

        long recruitId,
        String title,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate startDate,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate endDate,
        boolean permanentRecruitment,
        RecruitDepartment department,
        boolean isNew,
        boolean isCareer,
        int minCareer,
        int maxCareer,
        String workspaceName,
        EmploymentType employmentType,
        long interviewerIdx,
        String postcode, //우편번호
        String address, //주소
        String detailAddress, //상세주소
        String detail, //상세내역
        String recruitInfo, //자격 요건
        String treatmentInfo,//우대시항
        int applicationCount, //지원자 수
        boolean isTempSave, ///임시저장 여부
        boolean isStop, //공고 중단 여부
        boolean isHidden, //비공개 여부
        boolean isClosed,//조기종료 여부
        boolean resumeHasCareer, // 지원서 경력 여부
        boolean resumeDesiredPay, //희망 연봉 여부
        boolean resumeLanguage, //언어 여부
        boolean resumeSubmitted, //이력서 제출 여부
        boolean resumePortfolioSubmitted, //포트폴리오 제출 여부
        boolean resumeQuestionFiled, //이력서 질문지 여부

        long registerEmployeeId,
        long modifyEmployeeId, //최종 수정자

        List<GetRecruitLinkListResponseDTO> recruitLinkList, // 채용 링크
        List<GetRecruitBenefitListResponseDTO> recruitBenefitList, //체용혜택
        List<GetRecruitStepListResponseDTO> recruitStepList, //채용단계

        boolean isDeleted//삭제 여부
) {

    public static GetRecruitInfoResponseDTO fromEntity(
            Recruit recruit,List<GetRecruitLinkListResponseDTO> recruitLinkList,
            List<GetRecruitBenefitListResponseDTO> recruitBenefitList,
            List<GetRecruitStepListResponseDTO> recruitStepList) {

        return new GetRecruitInfoResponseDTO(
                recruit.getId(),
                recruit.getTitle(),
                recruit.getStartDate(),
                recruit.getEndDate(),
                recruit.isPermanentRecruitment(),
                recruit.getDepartment(),
                recruit.isNew(),
                recruit.isCareer(),
                recruit.getMinCareer(),
                recruit.getMaxCareer(),
                recruit.getWorkspaceName(),
                recruit.getEmploymentType(),
                recruit.getInterviewerIdx(),
                recruit.getPostcode(),
                recruit.getAddress(),
                recruit.getDetailAddress(),
                recruit.getDetail(),
                recruit.getRecruitInfo(),
                recruit.getTreatmentInfo(),
                recruit.getApplicationCount(),
                recruit.isTempSave(),
                recruit.isStop(),
                recruit.isHidden(),
                recruit.isClosed(),
                recruit.isResumeHasCareer(),
                recruit.isResumeDesiredPay(),
                recruit.isResumeLanguage(),
                recruit.isResumeSubmitted(),
                recruit.isResumePortfolioSubmitted(),
                recruit.isResumeQuestionFiled(),
                recruit.getRegisterEmployee().getId(),
                recruit.getModifyEmployeeId(),
                recruitLinkList,
                recruitBenefitList,
                recruitStepList,
                recruit.isDeleted()
        );
    }
}
