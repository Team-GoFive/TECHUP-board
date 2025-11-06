package com.kt.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kt.board.domain.dto.request.BoardCreateRequest;
import com.kt.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/boards")
@RestController
public class BoardController {
	private final BoardService boardService;

	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void createBoard(@RequestBody BoardCreateRequest request) {
		boardService.boardCreate(request);
	}
}
