package com.kt.board.service;

import com.kt.board.domain.dto.request.PostCreateRequest;
import com.kt.board.domain.dto.request.PostRequest;
import com.kt.board.domain.dto.request.PostUpdateRequest;

public interface PostService {

    void create(Long boardId, PostRequest.Create request);

	void update(Long postId, PostRequest.Update request);

	void remove(Long postId);

}
