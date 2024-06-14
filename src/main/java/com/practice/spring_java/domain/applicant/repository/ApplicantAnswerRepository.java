package com.practice.spring_java.domain.applicant.repository;

import com.practice.spring_java.domain.applicant.entity.ApplicantAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantAnswerRepository
        extends JpaRepository<ApplicantAnswer, Long> {

    List<ApplicantAnswer> findByApplicantId(Long applicantId);
}
