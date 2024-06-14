package com.practice.spring_java.domain.applicant.repository;

import com.practice.spring_java.domain.applicant.entity.Applicant;
import com.practice.spring_java.domain.applicant.enums.RecruitmentStage;

import java.util.List;

public interface ApplicantRepositoryCustom {

    List<Applicant> findOnRecruitmentStageApplicant(RecruitmentStage recruitmentStage);
}
