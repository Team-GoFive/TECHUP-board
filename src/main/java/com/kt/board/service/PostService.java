package com.kt.board.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kt.board.common.api.searchtype.PostSearchType;
import com.kt.board.domain.dto.request.PostRequest;
import com.kt.board.domain.dto.response.PostResponse;

public interface PostService {

	Long create(Long boardId, PostRequest.Create request);

	void update(Long postId, PostRequest.Update request);

	void remove(Long postId);

	Page<PostResponse.Search> getPosts(String keyword, PostSearchType searchType, Pageable pageable);
}
