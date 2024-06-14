package com.practice.spring_java.domain.recruit.repository;

import com.practice.spring_java.domain.recruit.entity.RecruitPaper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitPaperRepository
        extends JpaRepository<RecruitPaper, Long> {

}
