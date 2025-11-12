package com.kt.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kt.board.domain.dto.response.PostResponse;

public interface PostQueryDslRepository {
	Page<PostResponse.Search> search(String title, String contents, String all, Pageable pageable);
}
