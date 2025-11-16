package com.kt.board.domain.entity;

import com.kt.board.constants.Gender;
import com.kt.board.constants.UserRole;
import com.kt.board.domain.entity.common.BaseEntity;

import com.kt.board.util.ValidationUtil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "\"user\"")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private int age;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRole role;

	protected UserEntity(
		String name,
		String encodedPassword,
		Gender gender,
		String email,
		int age,
		UserRole role
	) {
		this.name = name;
		this.password = encodedPassword;
		this.gender = gender;
		this.email = email;
		this.age = age;
		this.role = role;
	}

	public static UserEntity create(
		final String name,
		final String encodedPassword,
		final Gender gender,
		final String email,
		final int age,
		final UserRole role
	) {
		fieldValidator(name, encodedPassword, gender, email, age, role);
		return new UserEntity(name, encodedPassword, gender, email, age, role);
	}

	private static void fieldValidator(
		final String name,
		final String encodedPassword,
		final Gender gender,
		final String email,
		final int age,
		final UserRole role) {
		ValidationUtil.validateNotBlank(name, "유저이름");
		ValidationUtil.validateNotBlank(encodedPassword, "유저 비밀번호");
		ValidationUtil.validationNotNullEnum(gender, "유저 성별");
		ValidationUtil.validateNotBlank(email, "유저 이메일");
		ValidationUtil.validatePositive(age, "유저 나이");
		ValidationUtil.validationNotNullEnum(role, "유저 권한");
	}
}
