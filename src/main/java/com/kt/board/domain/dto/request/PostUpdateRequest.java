package com.kt.board.domain.dto.request;

import com.kt.board.domain.model.post.PostDisclosureType;

public record PostUpdateRequest(
	String title,
	String content,
	PostDisclosureType disclosureType
) {
}
