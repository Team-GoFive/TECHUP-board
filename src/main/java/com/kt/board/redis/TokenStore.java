package com.kt.board.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class TokenStore {

    private final StringRedisTemplate redisTemplate;
    private static final String PREFIX = "auth:token:";

    public boolean isValidToken(Long userId, String token) {
        String key = PREFIX + token;
        String storedUserId = redisTemplate.opsForValue().get(key);
        return storedUserId != null && storedUserId.equals(String.valueOf(userId));
    }

    public void saveToken(String token, Long userId) {
        redisTemplate.opsForValue().set(PREFIX + token, userId.toString(), Duration.ofMinutes(5));
    }

    public void deleteToken(String token) {
        redisTemplate.delete(PREFIX + token);
    }
}