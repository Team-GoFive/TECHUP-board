package com.kt.board.service;

import com.kt.board.domain.dto.request.UserCreateRequest;

public interface UserService {

    void create(UserCreateRequest request);
}
