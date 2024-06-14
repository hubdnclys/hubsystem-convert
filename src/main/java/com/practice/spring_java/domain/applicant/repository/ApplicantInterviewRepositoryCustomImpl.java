package com.practice.spring_java.domain.applicant.repository;

import com.practice.spring_java.domain.applicant.entity.ApplicantInterview;
import com.practice.spring_java.domain.applicant.entity.QApplicantInterview;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ApplicantInterviewRepositoryCustomImpl implements ApplicantInterviewRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @PersistenceContext
    private EntityManager em;

    public ApplicantInterviewRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<ApplicantInterview> findApplicantInterviewByDate(LocalDate date) {
        QApplicantInterview applicantInterview = QApplicantInterview.applicantInterview;

        // 입력된 LocalDate를 LocalDateTime으로 변환하여 시작 시간과 종료 시간을 설정
        // LocalDate와 LocalDateTime을 비교하기 위해 바꾸어 진행해야 함
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1); // 해당 날짜의 마지막 시간

        return queryFactory.selectFrom(applicantInterview)
                .where(
                        applicantInterview.interviewDate.between(startOfDay, endOfDay)
                )
                .fetch();
    }
}
