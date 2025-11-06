package com.kt.board.service;

import com.kt.board.domain.dto.request.PostCreateRequest;

public interface PostService {

	public void create(Long boardId, PostCreateRequest request);
}
