package com.kt.board.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public record BoardUpdateRequest(

        @NotBlank
	    String name
) {
}
