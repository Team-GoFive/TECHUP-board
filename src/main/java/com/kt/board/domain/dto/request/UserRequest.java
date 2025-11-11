package com.kt.board.domain.dto.request;

import static lombok.AccessLevel.*;

import com.kt.board.constants.Gender;
import com.kt.board.constants.UserRole;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PROTECTED)
public class UserRequest {

	@Schema(name = "UserRequest.Create")
	public record Create(
		@NotBlank
		String name,

		@NotBlank
		String password,

		@NotNull
		Gender gender,

		@NotBlank
		String email,

		@NotNull
		Integer age,

		@NotNull
		UserRole role
	) {
	}
}
