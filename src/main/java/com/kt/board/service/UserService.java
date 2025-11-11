package com.kt.board.service;

import com.kt.board.domain.dto.request.UserRequest;

public interface UserService {

    void create(UserRequest.Create request);
}
