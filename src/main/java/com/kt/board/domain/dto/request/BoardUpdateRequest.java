package com.kt.board.domain.dto.request;

import com.kt.board.constants.BoardStatus;

public record BoardUpdateRequest(
	String name,
	BoardStatus status
) {
}
