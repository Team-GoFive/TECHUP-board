package com.kt.board.service;

import com.kt.board.domain.dto.request.BoardRequest;

public interface BoardService {
	void create(BoardRequest.Create request);
	void update(Long boardId, BoardRequest.Update request);
}
