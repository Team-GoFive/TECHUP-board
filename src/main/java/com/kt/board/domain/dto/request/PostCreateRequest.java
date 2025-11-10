package com.kt.board.domain.dto.request;

import com.kt.board.constants.PostDisclosureType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostCreateRequest(

    @NotBlank
	String title,

    @NotBlank
	String content,

    @NotNull
	PostDisclosureType disclosureType,

    @NotNull
	Long userId
) {
}
