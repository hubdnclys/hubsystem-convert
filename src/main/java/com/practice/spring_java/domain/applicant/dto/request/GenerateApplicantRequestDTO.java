package com.practice.spring_java.domain.applicant.dto.request;

import com.practice.spring_java.domain.applicant.entity.Applicant;
import com.practice.spring_java.domain.applicant.enums.RecruitmentStage;
import com.practice.spring_java.domain.recruit.entity.Recruit;
import com.practice.spring_java.domain.recruit.enums.RecruitDepartment;
import jakarta.validation.constraints.NotNull;

public record GenerateApplicantRequestDTO(

        @NotNull(message = "recruitId은 필수 값 입니다.")
        Long recruitId,

        @NotNull(message = "name은 필수값입니다")
        String name,

        @NotNull(message = "phone은 필수값입니다")
        String phone,

        @NotNull(message = "email은 필수값입니다")
        String email,

        @NotNull(message = "recruitDepart은 필수값입니다")
        RecruitDepartment department,

        boolean isNew, //
        int experienceYear, //경렭
        String applicationPath, //지원 경로

        RecruitmentStage recruitmentStage,
        String resumeUrl,
        String portfolio
) {

    public Applicant toEntity(Recruit recruit) {
        return Applicant.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .department(department)
                .isNew(isNew)
                .experienceYear(experienceYear)
                .applicationPath(applicationPath)
                .recruitmentStage(recruitmentStage)
                .resumeUrl(resumeUrl)
                .portfolioUrl(portfolio)
                .recruit(recruit)
                .build();
    }
}
