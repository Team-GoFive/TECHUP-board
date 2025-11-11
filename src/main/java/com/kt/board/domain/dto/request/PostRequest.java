package com.kt.board.domain.dto.request;

import static lombok.AccessLevel.*;

import com.kt.board.constants.PostDisclosureType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PROTECTED)
public class PostRequest {

	@Schema(name = "PostRequest.Create")
	public record Create(
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

	@Schema(name = "PostRequest.Update")
	public record Update(
		@NotBlank
		String title,

		@NotBlank
		String content,

		@NotNull
		PostDisclosureType disclosureType
	) {
	}

}
