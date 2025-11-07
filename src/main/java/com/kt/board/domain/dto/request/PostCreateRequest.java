package com.kt.board.domain.dto.request;

import com.kt.board.constants.PostDisclosureType;

public record PostCreateRequest(
	String title,
	String content,
	PostDisclosureType disclosureType,
	Long userId
) {
}
