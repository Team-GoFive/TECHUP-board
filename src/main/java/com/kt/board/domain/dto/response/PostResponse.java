package com.kt.board.domain.dto.response;

import com.querydsl.core.annotations.QueryProjection;

public class PostResponse {
	public record Search(
		Long id,
		String title,
		String content
	) {
		@QueryProjection
		public Search {
		}
	}
}
