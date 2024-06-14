package com.practice.spring_java.domain.applicant.repository;

import com.practice.spring_java.domain.applicant.entity.ApplicantInterview;

import java.time.LocalDate;
import java.util.List;

public interface ApplicantInterviewRepositoryCustom {

    List<ApplicantInterview> findApplicantInterviewByDate(LocalDate date);
}
