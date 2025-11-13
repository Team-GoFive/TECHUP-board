package com.kt.board;

import java.util.UUID;

import com.kt.board.constants.Gender;
import com.kt.board.constants.UserRole;
import com.kt.board.domain.entity.UserEntity;

public class UserGenerator {
	public static UserEntity generateMemberUser() {
		return UserEntity.create(
			"user_" + UUID.randomUUID(),
			"password",
			Gender.MALE,
			UUID.randomUUID() + "@test.com",
			1,
			UserRole.MEMBER
		);
	}
}
