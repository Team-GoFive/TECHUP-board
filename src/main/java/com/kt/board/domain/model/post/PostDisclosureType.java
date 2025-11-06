package com.kt.board.domain.model.post;

// 게시글 공개/비공개 여부 설정
public enum PostDisclosureType {
	PUBLIC("공개"),
	PRIVATE("비공개");

	private final String description;

	PostDisclosureType(String description) {
		this.description = description;
	}
}
