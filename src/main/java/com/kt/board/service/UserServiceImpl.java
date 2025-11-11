package com.kt.board.service;

import com.kt.board.domain.dto.request.UserRequest;
import com.kt.board.domain.entity.UserEntity;
import com.kt.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void create(UserRequest.Create request) {
        UserEntity user = UserEntity.create(
                request.name(),
                passwordEncoder.encode(request.password()),
                request.gender(),
                request.email(),
                request.age(),
                request.role()
        );
        userRepository.save(user);
    }
}
