package com.kt.board.constants;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PostDisclosureType {
	PUBLIC("공개"),
	PRIVATE("비공개");

	private final String description;

}
