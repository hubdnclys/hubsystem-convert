package com.practice.spring_java.domain.recruit.repository;

import com.practice.spring_java.domain.recruit.entity.RecruitBenefits;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitBenefitsRepository
        extends JpaRepository<RecruitBenefits, Long> {

}
