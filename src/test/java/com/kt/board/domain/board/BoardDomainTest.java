package com.kt.board.domain.board;

import com.kt.board.constants.Gender;
import com.kt.board.constants.UserRole;
import com.kt.board.domain.entity.BoardEntity;

import com.kt.board.domain.entity.UserEntity;

import com.kt.board.exception.FieldValidationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class BoardDomainTest {

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	UserEntity user;

	@BeforeEach
	void init() {
		user = UserEntity.create(
			"관리자",
			passwordEncoder.encode("123123!"),
			Gender.MALE,
			"test@email.com",
			29,
			UserRole.ADMIN
		);
	}

	@Test
	void 게시판_객체_생성_성공() {
		BoardEntity.create("테스트_게시판", user);
	}

	@Test
	void 게시판_객체_생성_실패_name_null() {
		assertThrowsExactly(
			FieldValidationException.class, () ->
				BoardEntity.create(
					null,
					user
				)
		);
	}

	@Test
	void 게시판_객체_생성_실패_name_blank() {
		assertThrowsExactly(
			FieldValidationException.class, () ->
				BoardEntity.create(
					" ",
					user
				)
		);
	}

	@Test
	void 게시판_객체_생성_실패_name_empty() {
		assertThrowsExactly(
			FieldValidationException.class, () ->
				BoardEntity.create(
					"",
					user
				)
		);
	}

}
