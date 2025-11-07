package com.kt.board.domain.dto.request;

import com.kt.board.domain.entity.UserEntity;

public record BoardCreateRequest(
        Long createdById,
	    String name
) {
}
