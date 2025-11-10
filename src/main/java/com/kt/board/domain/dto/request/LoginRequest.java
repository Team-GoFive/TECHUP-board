package com.kt.board.domain.dto.request;

public record LoginRequest(
        String email,
        String password
) {
}
