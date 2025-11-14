package com.kt.board.domain.user;

import com.kt.board.constants.Gender;
import com.kt.board.constants.UserRole;
import com.kt.board.domain.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.*;


import com.kt.board.exception.FieldValidationException;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserDomainTest {

	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Test
	void 유저_객체_생성_성공() {
		UserEntity.create(
			" ",
			passwordEncoder.encode("123123!"),
			Gender.MALE,
			"test@email.com",
			9,
			UserRole.MEMBER
		);
	}

	@Test
	void 유저_객체_생성_실패_유저명_null() {
		assertThrowsExactly(
			FieldValidationException.class, () ->
				UserEntity.create(
				null,
					passwordEncoder.encode("123123!"),
				Gender.MALE,
				"test@email.com",
				9,
				UserRole.MEMBER
			)
		);
	}

	@Test
	void 유저_객체_생성_실패_비밀번호_60자_이하() {
		//$2a$10$D4TNRLjLI7RbZF7rwp8Pa.VlNaijLkVzeEpUcDEMH0KZKjMShIb5a <- 정상 비밀번호 길이
		assertThrowsExactly(
			FieldValidationException.class, () ->
				UserEntity.create(
					null,
					"$2a$10$D4TNRLjLI7RbZF7rwp8Pa.VlNaijLkVzeEpUcDEMH0KZKjMShIb5",
					Gender.MALE,
					"test@email.com",
					9,
					UserRole.MEMBER
				)
		);
	}

}
