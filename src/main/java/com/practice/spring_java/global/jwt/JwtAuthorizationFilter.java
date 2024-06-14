package com.practice.spring_java.global.jwt;

import com.practice.spring_java.domain.employee.repository.EmployeeRepository;
import com.practice.spring_java.global.security.PrincipalDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * JWT를 이용한 인가 (Authorization) 코드
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final EmployeeRepository employeeRepository;
    private final JwtProvider jwtProvider;

    public JwtAuthorizationFilter(
            EmployeeRepository employeeRepository,
            JwtProvider jwtProvider
    ) {
        this.employeeRepository = employeeRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        //header에서 가져옴
        List<String> headerValues = Collections.list(request.getHeaders("Authorization"));
        String accessToken = headerValues.stream()
                .findFirst()
                .map(header -> header.replace("Bearer ", ""))
                .orElse(null);

        //현재 토큰을 사용 하여 인증을 시도 합니다.
        Authentication authentication = getUsernamePasswordAuthenticationToken(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }

    /**
     * JWT 토큰으로 User를 찾아서 UsernamePasswordAuthenticationToken를 만들어서 반환한다.
     */
    private Authentication getUsernamePasswordAuthenticationToken(String token) {
        if (token == null) {
            return null;
        }
        String email = jwtProvider.getEmail(token);
        if (email != null) {
            return employeeRepository.findByEmail(email)
                    .map(PrincipalDetails::new)
                    .map(principalDetails -> new UsernamePasswordAuthenticationToken(
                            principalDetails, // principal
                            null, // credentials
                            principalDetails.getAuthorities()
                    )).orElseThrow(IllegalAccessError::new);
        }
        return null; // 유저가 없으면 NULL
    }
}