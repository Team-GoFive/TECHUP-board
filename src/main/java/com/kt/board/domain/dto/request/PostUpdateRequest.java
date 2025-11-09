package com.kt.board.domain.dto.request;

import com.kt.board.constants.PostDisclosureType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostUpdateRequest(

        @NotBlank
        String title,

        @NotBlank
        String content,

        @NotNull
        PostDisclosureType disclosureType
) {
}
