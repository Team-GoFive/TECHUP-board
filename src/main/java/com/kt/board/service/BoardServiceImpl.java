package com.kt.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.board.domain.dto.request.BoardCreateRequest;
import com.kt.board.domain.dto.request.BoardUpdateRequest;
import com.kt.board.domain.model.Board;
import com.kt.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardRepository boardRepository;

	@Transactional
	@Override
	public void create(BoardCreateRequest request) {

		Board board = Board.create(request.name());
		boardRepository.save(board);
	}

	@Transactional
	@Override
	public void update(Long boardId, BoardUpdateRequest request) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("Board를 찾을 수 없습니다.: " + boardId));

		board.update(request.name(), request.status());
	}
}
