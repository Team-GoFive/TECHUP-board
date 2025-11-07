package com.kt.board.domain.dto.request;

import com.kt.board.constants.PostDisclosureType;

public record PostUpdateRequest(
	String title,
	String content,
	PostDisclosureType disclosureType
) {
}
