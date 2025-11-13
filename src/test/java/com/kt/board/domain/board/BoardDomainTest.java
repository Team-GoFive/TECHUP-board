package com.kt.board.domain.board;

import com.kt.board.constants.Gender;
import com.kt.board.constants.UserRole;
import com.kt.board.domain.entity.BoardEntity;

import com.kt.board.domain.entity.UserEntity;

import com.kt.board.exception.CustomException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class BoardDomainTest {

	UserEntity user;

	@BeforeEach
	void init(TestInfo testInfo) {
		String displayName = testInfo.getDisplayName();
		if (displayName.equals("게시판_생성_성공") ||
				displayName.equals("게시판_생성_실패")) {
			user = UserEntity.create(
				"황테스트",
				"1231231!",
				Gender.MALE,
				"test@email.com",
				20,
				UserRole.MEMBER
			);
		}
	}

	@Test
	@DisplayName("게시판_생성_성공")
	void 게시판_객체_생성_성공() {
		BoardEntity board = BoardEntity.create(
			"testBoardName",
			user
		);
		assertEquals("testBoardName", board.getName());
		assertEquals(user, board.getCreatedBy());
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"   "})
	@DisplayName("게시판_생성_실패")
	void 게시판_객체_생성_실패(String boardName) {
		assertThrowsExactly(CustomException.class, () ->
			BoardEntity.create(
				boardName,
				user
			)
		);
	}
}
