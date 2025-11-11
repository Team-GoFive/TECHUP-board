package com.kt.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.board.domain.dto.request.BoardRequest;
import com.kt.board.domain.entity.BoardEntity;
import com.kt.board.domain.entity.UserEntity;
import com.kt.board.repository.BoardRepository;
import com.kt.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;

	@Transactional
	@Override
	public void create(BoardRequest.Create request) {
		UserEntity createdBy = userRepository.findById(request.createdById())
			.orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
		BoardEntity board = BoardEntity.create(request.name(), createdBy);
		boardRepository.save(board);
	}

	@Transactional
	@Override
	public void update(Long boardId, BoardRequest.Update request) {
		BoardEntity board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("Board를 찾을 수 없습니다.: " + boardId));

		board.update(request.name());
	}
}
