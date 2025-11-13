package com.kt.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kt.board.common.api.searchtype.PostSearchType;
import com.kt.board.domain.dto.response.PostResponse;

public interface PostQueryDslRepository {
	Page<PostResponse.Search> search(String keyword, PostSearchType searchType, Pageable pageable);
}
