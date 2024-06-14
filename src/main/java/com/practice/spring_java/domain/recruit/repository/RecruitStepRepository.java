package com.practice.spring_java.domain.recruit.repository;

import com.practice.spring_java.domain.recruit.entity.RecruitStep;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitStepRepository
        extends JpaRepository<RecruitStep, Long> {

}
