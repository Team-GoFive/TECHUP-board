package com.kt.board.service;

import com.kt.board.constants.message.ErrorCode;
import com.kt.board.domain.dto.request.LoginRequest;
import com.kt.board.domain.entity.UserEntity;
import com.kt.board.exception.CustomException;
import com.kt.board.redis.TokenStore;
import com.kt.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final TokenStore tokenStore;

    @Override
    public String login(LoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        if (!user.getPassword().equals(request.password()))
            new IllegalArgumentException("test exception");
        String token = UUID.randomUUID().toString();
        tokenStore.saveToken(token, user.getId());
        return token;
    }
}
