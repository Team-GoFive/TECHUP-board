package com.kt.board.service;

import com.kt.board.constants.message.ErrorCode;
import com.kt.board.exception.CustomException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.board.domain.dto.request.BoardRequest;
import com.kt.board.domain.entity.BoardEntity;
import com.kt.board.domain.entity.UserEntity;
import com.kt.board.repository.BoardRepository;
import com.kt.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.util.StringUtils;

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
		validationBoardName(request.name());
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

	private void validationBoardName(String boardName) {
		if (!StringUtils.hasText(boardName)) {
			throw new CustomException(ErrorCode.BOARD_NAME_REQUIRED);
		}
	}

}
