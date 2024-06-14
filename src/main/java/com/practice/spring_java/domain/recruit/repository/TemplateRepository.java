package com.practice.spring_java.domain.recruit.repository;

import com.practice.spring_java.domain.recruit.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository
        extends JpaRepository<Template, Long> {

}
