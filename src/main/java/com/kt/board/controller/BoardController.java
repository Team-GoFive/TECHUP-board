package com.kt.board.controller;

import com.kt.board.common.api.ApiResult;
import com.kt.board.domain.dto.request.BoardRequest;
import com.kt.board.domain.dto.request.PostRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<ApiResult<Void>> create(
            @RequestBody @Valid BoardRequest.Create request
    ) {
		boardService.create(request);
        return wrap(null);
	}

	@PostMapping("/{boardId}/posts")
	public ResponseEntity<ApiResult<Void>> createPost(
            @PathVariable Long boardId,
            @RequestBody @Valid PostRequest.Create request) {
		postService.create(boardId, request);
        return wrap(null);
	}

	@PutMapping("/{boardId}")
	public ResponseEntity<ApiResult<Void>> update(
            @PathVariable Long boardId,
            @RequestBody @Valid BoardRequest.Update request) {
		boardService.update(boardId, request);
        return wrap(null);
	}
}
