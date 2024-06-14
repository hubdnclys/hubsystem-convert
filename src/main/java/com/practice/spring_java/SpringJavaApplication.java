package com.practice.spring_java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
public class SpringJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringJavaApplication.class, args);
    }
}
