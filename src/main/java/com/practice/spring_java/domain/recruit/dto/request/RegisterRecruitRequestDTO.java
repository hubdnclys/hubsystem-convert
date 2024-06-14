package com.practice.spring_java.domain.recruit.dto.request;

import com.practice.spring_java.domain.employee.entity.Employee;
import com.practice.spring_java.domain.recruit.entity.Recruit;
import com.practice.spring_java.domain.recruit.enums.EmploymentType;
import com.practice.spring_java.domain.recruit.enums.RecruitDepartment;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record RegisterRecruitRequestDTO(
        @NotNull(message = "email은 필수값입니다")
        String title,

        @NotNull(message = "startDate은 필수값입니다")
        LocalDate startDate,
        @NotNull(message = "endDate은 필수값입니다")
        LocalDate endDate,
        boolean permanentRecruitment, //상시 채용 여부
        @NotNull(message = "recruitDepartment은 필수값입니다")
        RecruitDepartment recruitDepartment,
        @NotNull(message = "isNew은 필수값입니다")
        boolean isNew, //신입 모집여뷰
        @NotNull(message = "isCareer은 필수값입니다")
        boolean isCareer, //경력 모집여부

        int minCareer, //최소년차
        int maxCareer, //최대년차

        @NotNull(message = "workspaceName은 필수값입니다")
        String workspaceName, //근무지명
        @NotNull(message = "employmentType은 필수값입니다")
        EmploymentType employmentType, //고용형태
        @NotNull(message = "interviewerIdx은 필수값입니다")
        long interviewerIdx,

        String postcode,
        String address,
        String detail_address,

        String detail, //상세내역
        String recruitInfo, // 자격요건
        String treatmentInfo,// 우대사항

        List<RegisterRecruitBenefitsRequestDTO> recruitBenefitsList, // 채용 혜택,

        List<RegisterRecruitStepRequestDTO> recruitStepList, //채용 단계 등록

        boolean resumeHasCareer, // 지원서 경력 여부
        boolean resumeDesiredPay, //희망 연봉 여부
        boolean resumeLanguage, //언어 여부
        boolean resumeSubmitted, //이력서 제출 여부
        boolean resumePortfolioSubmitted, //포트폴리오 제출 여부
        boolean resumeQuestionFiled, //이력서 질문 사용 여부
        List<RegisterRecruitLinkRequestDTO> linkList // 링크 설정
) {
    public Recruit toEntity(
            Employee regitserEmployee, long modifiedEmployeeIdx) {
        return Recruit.builder()
                .title(title)
                .startDate(startDate)
                .endDate(endDate)
                .permanentRecruitment(permanentRecruitment)
                .recruitDepartment(recruitDepartment)
                .isNew(isNew)
                .isCareer(isCareer)
                .minCareer(minCareer)
                .maxCareer(maxCareer)
                .workspaceName(workspaceName)
                .employmentType(employmentType)
                .interviewerIdx(interviewerIdx)
                .postcode(postcode)
                .address(address)
                .detailAddress(detail_address)
                .detail(detail)
                .recruitInfo(recruitInfo)
                .treatmentInfo(treatmentInfo)
                .modifyEmployee(modifiedEmployeeIdx)
                .registerEmployee(regitserEmployee)
                .resumeHasCareer(resumeHasCareer)
                .resumeDesiredPay(resumeDesiredPay)
                .resumeLanguage(resumeLanguage)
                .resumeSubmitted(resumeSubmitted)
                .resumePortfolioSubmitted(resumePortfolioSubmitted)
                .resumeQuestionFiled(resumeQuestionFiled)
                .build();
    }
}
