package com.kt.board.domain.dto.request;

import com.kt.board.domain.model.post.PostDisclosureType;

public record PostCreateRequest(
	String title,
	String content,
	PostDisclosureType disclosureType,
	Long userId
) {
}
