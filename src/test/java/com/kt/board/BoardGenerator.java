package com.kt.board;

import java.util.UUID;

import com.kt.board.domain.entity.BoardEntity;
import com.kt.board.domain.entity.UserEntity;

public class BoardGenerator {
	public static BoardEntity generateBoard(UserEntity user) {
		return BoardEntity.create("name_" + UUID.randomUUID(), user);
	}
}
