package com.kt.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.board.domain.dto.request.BoardCreateRequest;
import com.kt.board.domain.model.Board;
import com.kt.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardRepository boardRepository;

	@Transactional
	@Override
	public void boardCreate(BoardCreateRequest request) {

		Board board = Board.create(request.name());
		boardRepository.save(board);
	}
}
