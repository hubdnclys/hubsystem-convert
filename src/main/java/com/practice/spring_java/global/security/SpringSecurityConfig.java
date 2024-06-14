package com.practice.spring_java.global.security;

import com.practice.spring_java.global.jwt.JwtAuthenticationFilter;
import com.practice.spring_java.global.jwt.JwtAuthorizationFilter;
import com.practice.spring_java.global.util.CustomResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   HandlerMappingIntrospector introspector) throws Exception {

        // CORS 설정 추가
        http.cors(cors -> cors
                .configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.applyPermitDefaultValues();
                    configuration.addAllowedOriginPattern("");
                    configuration.setAllowedMethods(
                            Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "HEAD"));

                    return configuration;
                }));

        // basic authentication
        http.httpBasic(AbstractHttpConfigurer::disable); // basic authentication filter 비활성화
        // csrf
        http.csrf(AbstractHttpConfigurer::disable);
        // remember-me
        http.rememberMe(AbstractHttpConfigurer::disable);
        // stateless
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // jwt filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthorizationFilter, BasicAuthenticationFilter.class);

        http.authorizeHttpRequests(authz -> authz
                /*
                .requestMatchers(new AntPathRequestMatcher("ant matcher")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("role sample")).hasRole("ADMIN")
                .requestMatchers(HttpMethod.OPTIONS, "/basket/**").permitAll() // OPTIONS 메서드에 대한 권한 허용
                .requestMatchers(new AntPathRequestMatcher("role sample", HttpMethod.POST.name())).hasRole("ADMIN") */
                .requestMatchers(new AntPathRequestMatcher("/login/**")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v1/employee/signup")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/access-token")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v1/employee")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v1/recruit/{recruitId}")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v1/recruit/")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v1/employee/department")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v1/employee/password")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v1/mail")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v1/applicant")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v1/applicant/interview")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v1/applicant/{applicantId}")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v1/applicant/{applicantId}/interview")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v1/applicant/recruitment-stage")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/v1/mail/verify-code")).permitAll()
                .anyRequest().authenticated());

        http.exceptionHandling(exceptionHandling -> {
            exceptionHandling.authenticationEntryPoint(
                    (request, response, authException) -> CustomResponseUtil.failAuthentication(response,
                            "로그인을 진행해 주세요", HttpStatus.UNAUTHORIZED));

            exceptionHandling.accessDeniedHandler(
                    (request, response, accessDeniedException) -> CustomResponseUtil.failAuthentication(response,
                            "접근 권한이 없습니다", HttpStatus.FORBIDDEN));
        });

        return http.build();
    }
}