package com.kt.board.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.board.domain.dto.request.PostUpdateRequest;
import com.kt.board.service.PostService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

	private final PostService postService;

	@PutMapping("/{postid}")
	public void updatePost(@PathVariable Long postid, @RequestBody PostUpdateRequest request) {
		postService.update(postid, request);
	}
}
