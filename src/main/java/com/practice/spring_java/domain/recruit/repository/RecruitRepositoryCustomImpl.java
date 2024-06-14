package com.practice.spring_java.domain.recruit.repository;


import com.practice.spring_java.domain.recruit.entity.QRecruit;
import com.practice.spring_java.domain.recruit.entity.Recruit;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecruitRepositoryCustomImpl implements RecruitRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @PersistenceContext
    private EntityManager em;

    public RecruitRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Recruit> findRecruitByRecruitTitle(String title) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QRecruit recruit = QRecruit.recruit;

        return queryFactory.selectFrom(recruit)
                .where(recruit.title.contains(title))
                .fetch();
    }
}
