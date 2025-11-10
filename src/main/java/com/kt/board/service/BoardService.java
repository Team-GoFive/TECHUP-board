package com.kt.board.service;

import com.kt.board.domain.dto.request.BoardCreateRequest;
import com.kt.board.domain.dto.request.BoardUpdateRequest;

public interface BoardService {
	void create(BoardCreateRequest request);
	void update(Long boardId, BoardUpdateRequest request);
}
