package com.kt.board.service;

import com.kt.board.domain.dto.request.BoardCreateRequest;

public interface BoardService {
	void boardCreate(BoardCreateRequest request);
}
