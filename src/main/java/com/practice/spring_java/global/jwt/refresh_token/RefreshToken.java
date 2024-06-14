package com.practice.spring_java.global.jwt.refresh_token;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 60 * 60 * 2)
//titmeToLive -> 만료시간 설정 초 단위
//timeToLive의 시간이 지나면 redis에 저장된 RefreshToken이 만료 됨

public class RefreshToken {

    //java.persistence.id가 아닌 opg.springframework.data.annotation.Id 를 import해야 함
    //Refresh Token은 Redis에 저장하기 때문에 JPA 의존성이 필요하지 않음. persistence로 하게되면 에러 발생
    @Id
    private String refreshToken;
    private Long userId;

    @Builder
    public RefreshToken(String refreshToken, Long userId) {
        this.refreshToken = refreshToken;
        this.userId = userId;
    }

    //역직렬화를 위한 기본 생성자
    public RefreshToken() {
    }
}