package com.kt.board.common.api.searchtype;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PostSearchType {
	TITLE("제목"),
	CONTENT("내용"),
	TITLE_OR_CONTENT("제목+내용");
	private final String description;
}
