package com.kt.board.service;

import com.kt.board.domain.dto.request.UserCreateRequest;
import com.kt.board.domain.model.User;
import com.kt.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void create(UserCreateRequest request) {
        Long userId = userRepository.selectMaxId() + 1;
        User user = User.create(
                userId,
                request.name(),
                request.password(),
                request.gender(),
                request.email(),
                request.age(),
                request.role()
        );
        userRepository.save(user);
    }
}
