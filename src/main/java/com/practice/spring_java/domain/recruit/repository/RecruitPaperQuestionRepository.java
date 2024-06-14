package com.practice.spring_java.domain.recruit.repository;

import com.practice.spring_java.domain.recruit.entity.RecruitPaperQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruitPaperQuestionRepository
        extends JpaRepository<RecruitPaperQuestion, Long> {

}
