package com.kt.board.domain.dto.request;

import com.kt.board.constants.Gender;
import com.kt.board.constants.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateRequest(

        @NotBlank
        String name,

        @NotBlank
        String password,

        @NotNull
        Gender gender,

        @NotBlank
        String email,

        @NotNull
        Integer age,

        @NotNull
        UserRole role
) {
}
