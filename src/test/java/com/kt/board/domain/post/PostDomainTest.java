package com.kt.board.domain.post;

import com.kt.board.constants.Gender;
import com.kt.board.constants.PostDisclosureType;
import com.kt.board.constants.UserRole;
import com.kt.board.domain.entity.BoardEntity;
import com.kt.board.domain.entity.PostEntity;
import com.kt.board.domain.entity.UserEntity;

import com.kt.board.exception.FieldValidationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class PostDomainTest {

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	UserEntity user;
	BoardEntity board;

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

		board = BoardEntity.create(
			"테스트_게시판",
			user
		);

	}

	@Test
	void 게시글_생성_성공() {
		PostEntity.create(
			"테스트_게시글",
			"테스트_게시글_내용",
			PostDisclosureType.PUBLIC,
			board,
			user
		);
	}

	@Test
	void 게시글_생성_실패_title_null() {
		assertThrowsExactly(
			FieldValidationException.class, () ->
				PostEntity.create(
					null,
					"테스트_게시글_내용",
					PostDisclosureType.PUBLIC,
					board,
					user
				)
		);
	}

	@Test
	void 게시글_생성_실패_title_blank() {
		assertThrowsExactly(
			FieldValidationException.class, () ->
				PostEntity.create(
					" ",
					"테스트_게시글_내용",
					PostDisclosureType.PUBLIC,
					board,
					user
				)
		);
	}

	@Test
	void 게시글_생성_실패_title_empty() {
		assertThrowsExactly(
			FieldValidationException.class, () ->
				PostEntity.create(
					"",
					"테스트_게시글_내용",
					PostDisclosureType.PUBLIC,
					board,
					user
				)
		);
	}

	@Test
	void 게시글_생성_실패_content_null() {
		assertThrowsExactly(
			FieldValidationException.class, () ->
				PostEntity.create(
					"테스트_게시글",
					null,
					PostDisclosureType.PUBLIC,
					board,
					user
				)
		);
	}

	@Test
	void 게시글_생성_실패_content_blank() {
		assertThrowsExactly(
			FieldValidationException.class, () ->
				PostEntity.create(
					"테스트_게시글",
					" ",
					PostDisclosureType.PUBLIC,
					board,
					user
				)
		);
	}

	@Test
	void 게시글_생성_실패_content_empty() {
		assertThrowsExactly(
			FieldValidationException.class, () ->
				PostEntity.create(
					"테스트_게시글",
					"",
					PostDisclosureType.PUBLIC,
					board,
					user
				)
		);
	}

	@Test
	void 게시글_생성_실패_disclosureType_null() {
		assertThrowsExactly(
			FieldValidationException.class, () ->
				PostEntity.create(
					"테스트_게시글",
					"테스트_게시글_내용",
					null,
					board,
					user
				)
		);
	}


}
