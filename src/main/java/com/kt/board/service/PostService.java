package com.kt.board.service;

import com.kt.board.domain.dto.request.PostCreateRequest;
import com.kt.board.domain.dto.request.PostUpdateRequest;
import com.kt.board.domain.model.post.PostDisclosureType;

public interface PostService {

	public void create(Long boardId, PostCreateRequest request);
	public void update(Long postId, PostUpdateRequest request);
	public void remove(Long postId);
}
