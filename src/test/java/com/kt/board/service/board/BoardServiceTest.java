package com.kt.board.service.board;

import com.kt.board.constants.Gender;
import com.kt.board.constants.UserRole;
import com.kt.board.domain.dto.request.BoardRequest;
import com.kt.board.domain.entity.BoardEntity;
import com.kt.board.domain.entity.UserEntity;
import com.kt.board.exception.CustomException;
import com.kt.board.repository.BoardRepository;
import com.kt.board.repository.UserRepository;
import com.kt.board.service.BoardServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class BoardServiceTest {

	@Autowired
	private BoardServiceImpl boardService;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private UserRepository userRepository;

	UserEntity user;

	@BeforeEach
	void init() {
		user = UserEntity.create(
			"황테스트",
			"1231231!",
			Gender.MALE,
			"test@email.com",
			20,
			UserRole.MEMBER
		);
		userRepository.save(user);
	}

	@Test
	void 게시판_객체_생성_성공() {
		BoardRequest.Create boardCreateRequest = new BoardRequest.Create(
			user.getId(),
			"테스트_게시판"
		);

		boardService.create(boardCreateRequest);

		BoardEntity foundedBoard = boardRepository.findByName(
			boardCreateRequest.name()
		).orElseGet(() -> null);

		assertNotNull(foundedBoard);
		System.out.println("board id : " + foundedBoard.getId());
		System.out.println("board name : " + foundedBoard.getName());

	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"   "})
	void 게시판_객체_생성_실패_게시판명_null(String boardName) {
		BoardRequest.Create boardCreateRequest = new BoardRequest.Create(
			user.getId(),
			boardName
		);

		assertThrowsExactly(CustomException.class, () ->
			boardService.create(boardCreateRequest)
		);

	}


}
