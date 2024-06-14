package com.practice.spring_java.domain.recruit.repository;

import com.practice.spring_java.domain.recruit.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitRepository
        extends JpaRepository<Recruit, Long> {
}
