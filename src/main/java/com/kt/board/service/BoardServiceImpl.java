package com.kt.board.service;

import com.kt.board.domain.entity.UserEntity;
import com.kt.board.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.board.domain.dto.request.BoardCreateRequest;
import com.kt.board.domain.dto.request.BoardUpdateRequest;
import com.kt.board.domain.entity.BoardEntity;
import com.kt.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardRepository boardRepository;
    private final UserRepository userRepository;

	@Transactional
	@Override
	public void create(BoardCreateRequest request) {
        UserEntity createdBy = userRepository.findById(request.createdById())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));
		BoardEntity board = BoardEntity.create(request.name(), createdBy);
		boardRepository.save(board);
	}

	@Transactional
	@Override
	public void update(Long boardId, BoardUpdateRequest request) {
		BoardEntity board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("Board를 찾을 수 없습니다.: " + boardId));

        board.update(request.name());
	}
}
