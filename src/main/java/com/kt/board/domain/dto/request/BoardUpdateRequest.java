package com.kt.board.domain.dto.request;

import com.kt.board.constants.BoardStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BoardUpdateRequest(

        @NotBlank
	    String name,

        @NotNull
        BoardStatus status
) {
}
