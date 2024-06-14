package com.practice.spring_java.domain.applicant.repository;

import com.practice.spring_java.domain.applicant.entity.ApplicantInterview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantInterviewRepository
        extends JpaRepository<ApplicantInterview, Long> {

    List<ApplicantInterview> findByApplicantId(Long id);
}
