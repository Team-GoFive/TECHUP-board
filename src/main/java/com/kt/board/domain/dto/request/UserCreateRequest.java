package com.kt.board.domain.dto.request;

import com.kt.board.constants.Gender;
import com.kt.board.constants.UserRole;

public record UserCreateRequest(
    String name,
    String password,
    Gender gender,
    String email,
    int age,
    UserRole role
) {
}
