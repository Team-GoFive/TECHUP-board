package com.kt.board;

import java.util.UUID;

import com.kt.board.constants.PostDisclosureType;
import com.kt.board.domain.entity.BoardEntity;
import com.kt.board.domain.entity.PostEntity;
import com.kt.board.domain.entity.UserEntity;

public class PostGenerator {

	public static PostEntity generatePost(String postTitle, BoardEntity board, UserEntity user) {
		return PostEntity.create(
			"title_" + UUID.randomUUID(),
			"content_" + UUID.randomUUID(),
			PostDisclosureType.PUBLIC,
			board,
			user
		);
	}

	public static PostEntity generatePost() {
		return PostEntity.create(
			"title_" + UUID.randomUUID(),
			"content_" + UUID.randomUUID(),
			PostDisclosureType.PUBLIC,
			null,
			null
		);
	}
}
