package com.kt.board.domain.dto.request;

import static lombok.AccessLevel.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PROTECTED)
public class BoardRequest {

	@Schema(name = "BoardRequest.Create")
	public record Create(
		@NotNull
		Long createdById,

		@NotBlank
		String name
	) {
	}

	@Schema(name = "BoardRequest.Update")
	public record Update(
		@NotBlank
		String name
	) {
	}

}
