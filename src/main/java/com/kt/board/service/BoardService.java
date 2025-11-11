package com.kt.board.service;

import com.kt.board.domain.dto.request.BoardCreateRequest;
import com.kt.board.domain.dto.request.BoardRequest;
import com.kt.board.domain.dto.request.BoardUpdateRequest;

public interface BoardService {
	void create(BoardRequest.Create request);
	void update(Long boardId, BoardRequest.Update request);
}
