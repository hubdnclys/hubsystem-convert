package com.practice.spring_java.domain.applicant.repository;

import com.practice.spring_java.domain.applicant.entity.Applicant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository
        extends JpaRepository<Applicant, Long> {

    Page<Applicant> findAll(Pageable pageable);

}
