package com.practice.spring_java.domain.applicant.repository;

import com.practice.spring_java.domain.applicant.entity.Applicant;
import com.practice.spring_java.domain.applicant.entity.QApplicant;
import com.practice.spring_java.domain.applicant.enums.RecruitmentStage;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApplicantRepositoryCustomImpl implements ApplicantRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @PersistenceContext
    private EntityManager em;

    public ApplicantRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Applicant> findOnRecruitmentStageApplicant(RecruitmentStage recruitmentStage) {
        QApplicant applicant = QApplicant.applicant;

        return queryFactory.selectFrom(applicant)
                .where(applicant.recruitmentStage.eq(recruitmentStage))
                .fetch();
    }
}
