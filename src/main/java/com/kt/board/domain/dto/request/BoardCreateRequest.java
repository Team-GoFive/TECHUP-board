package com.kt.board.domain.dto.request;


public record BoardCreateRequest(
        Long createdById,
	    String name
) {
}
