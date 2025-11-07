package com.kt.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kt.board.domain.dto.request.PostUpdateRequest;
import com.kt.board.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

	private final PostService postService;

	@PutMapping("/{postId}")
	@ResponseStatus(HttpStatus.OK)
	public void update(@PathVariable Long postId,
                       @RequestBody PostUpdateRequest request) {
		postService.update(postId, request);
	}
	@PatchMapping("/{postId}")
	@ResponseStatus(HttpStatus.OK)
	public void remove(@PathVariable Long postId) {
		postService.remove(postId);
	}
}
