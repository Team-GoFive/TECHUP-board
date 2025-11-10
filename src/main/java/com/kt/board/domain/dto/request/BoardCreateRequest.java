package com.kt.board.domain.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BoardCreateRequest(
        @NotNull
        Long createdById,

        @NotBlank
	    String name
) {
}
