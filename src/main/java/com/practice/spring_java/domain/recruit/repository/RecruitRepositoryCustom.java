package com.practice.spring_java.domain.recruit.repository;

import com.practice.spring_java.domain.recruit.entity.Recruit;

import java.util.List;

interface RecruitRepositoryCustom {

    List<Recruit> findRecruitByRecruitTitle(String title);
}
