package com.kt.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kt.board.domain.dto.request.BoardCreateRequest;
import com.kt.board.domain.dto.request.BoardUpdateRequest;
import com.kt.board.domain.dto.request.PostCreateRequest;
import com.kt.board.service.BoardService;
import com.kt.board.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/boards")
@RestController
public class BoardController {
	private final BoardService boardService;
	private final PostService postService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody BoardCreateRequest request) {
		boardService.create(request);
	}

	@PostMapping("/{boardId}")
	@ResponseStatus(HttpStatus.OK)
	public void create(@PathVariable Long boardId, @RequestBody PostCreateRequest request) {
		postService.create(boardId, request);
	}

	@PutMapping("/{boardId}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable Long boardId, @RequestBody BoardUpdateRequest request) {
		boardService.update(boardId, request);
	}
}
