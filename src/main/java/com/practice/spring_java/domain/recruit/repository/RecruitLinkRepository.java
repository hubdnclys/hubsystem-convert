package com.practice.spring_java.domain.recruit.repository;

import com.practice.spring_java.domain.recruit.entity.RecruitLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitLinkRepository
        extends JpaRepository<RecruitLink, Long> {

}
