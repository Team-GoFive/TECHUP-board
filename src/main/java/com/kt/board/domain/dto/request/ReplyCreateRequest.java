package com.kt.board.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReplyCreateRequest(

        @NotBlank
        String content,

        @NotNull
        Long userId
) {
}
