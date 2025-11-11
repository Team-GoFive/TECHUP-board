package com.kt.board.domain.dto.request;

import static lombok.AccessLevel.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PROTECTED)
public class ReplyRequest {

	@Schema(name = "ReplyRequest.Create")
	public record Create(
		@NotBlank
		String content,
		@NotNull
		Long userId
	) {
	}

}
