package com.kt.board.service;

import com.kt.board.domain.dto.request.PostCreateRequest;
import com.kt.board.domain.dto.request.PostUpdateRequest;

public interface PostService {

    void create(Long boardId, PostCreateRequest request);

	void update(Long postId, PostUpdateRequest request, String authorization);

	void remove(Long postId);

}
