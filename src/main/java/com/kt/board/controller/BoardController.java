package com.kt.board.controller;

import com.kt.board.common.api.ApiResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kt.board.domain.dto.request.BoardCreateRequest;
import com.kt.board.domain.dto.request.BoardUpdateRequest;
import com.kt.board.domain.dto.request.PostCreateRequest;
import com.kt.board.service.BoardService;
import com.kt.board.service.PostService;

import lombok.RequiredArgsConstructor;

import static com.kt.board.common.api.ApiResult.wrap;

@RequiredArgsConstructor
@RequestMapping("/api/boards")
@RestController
public class BoardController {
	private final BoardService boardService;
	private final PostService postService;

	@PostMapping
	public ResponseEntity<ApiResult<Void>> create(@RequestBody BoardCreateRequest request) {
		boardService.create(request);
        return wrap(null);
	}

	@PostMapping("/{boardId}")
	public ResponseEntity<ApiResult<Void>> create(@PathVariable Long boardId, @RequestBody PostCreateRequest request) {
		postService.create(boardId, request);
        return wrap(null);
	}

	@PutMapping("/{boardId}")
	public ResponseEntity<ApiResult<Void>> update(@PathVariable Long boardId, @RequestBody BoardUpdateRequest request) {
		boardService.update(boardId, request);
        return wrap(null);
	}
}
