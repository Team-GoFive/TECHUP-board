package com.kt.board.controller;

import static com.kt.board.common.api.ApiResult.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.board.common.api.ApiResult;
import com.kt.board.domain.dto.request.BoardRequest;
import com.kt.board.domain.dto.request.PostRequest;
import com.kt.board.service.BoardService;
import com.kt.board.service.PostService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/boards")
@RestController
public class BoardController {
	private final BoardService boardService;
	private final PostService postService;

	@PostMapping
	public ResponseEntity<ApiResult<Void>> create(
		@RequestBody @Valid BoardRequest.Create request
	) {
		boardService.create(request);
		return wrap(null);
	}

	@PostMapping("/{boardId}/posts")
	public ResponseEntity<ApiResult<Void>> createPost(
		@PathVariable Long boardId,
		@RequestBody @Valid PostRequest.Create request
	) {
		postService.create(boardId, request);
		return wrap(null);
	}

	@PutMapping("/{boardId}")
	public ResponseEntity<ApiResult<Void>> update(
		@PathVariable Long boardId,
		@RequestBody @Valid BoardRequest.Update request
	) {
		boardService.update(boardId, request);
		return wrap(null);
	}
}
