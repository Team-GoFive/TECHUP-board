package com.kt.board.domain.reply;

import com.kt.board.constants.Gender;
import com.kt.board.constants.PostDisclosureType;
import com.kt.board.constants.UserRole;
import com.kt.board.domain.entity.BoardEntity;
import com.kt.board.domain.entity.PostEntity;
import com.kt.board.domain.entity.ReplyEntity;
import com.kt.board.domain.entity.UserEntity;

import com.kt.board.exception.FieldValidationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class ReplyDomainTest {

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	UserEntity user;
	BoardEntity board;
	PostEntity post;


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

		post = PostEntity.create(
			"테스트_게시글",
			"테스트_게시글_내용",
			PostDisclosureType.PUBLIC,
			board,
			user
		);
	}

	@Test
	void 댓글_생성_성공() {
		ReplyEntity reply = ReplyEntity.create(
			"테스트 댓글 내용",
			post,
			user
		);
		assertNotNull(reply);
	}

	@Test
	void 댓글_생성_실패_content_null() {
		assertThrowsExactly(
			FieldValidationException.class, () ->
				ReplyEntity.create(
					null,
					post,
					user
				)
		);
	}

	@Test
	void 댓글_생성_실패_content_blank() {
		assertThrowsExactly(
			FieldValidationException.class, () ->
				ReplyEntity.create(
					" ",
					post,
					user
				)
		);
	}

	@Test
	void 댓글_생성_실패_content_empty() {
		assertThrowsExactly(
			FieldValidationException.class, () ->
				ReplyEntity.create(
					"",
					post,
					user
				)
		);
	}



}
