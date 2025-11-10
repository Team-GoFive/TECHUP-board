package com.kt.board.controller;

import com.kt.board.common.api.ApiResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import static com.kt.board.common.api.ApiResult.wrap;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

	private final PostService postService;

	@PutMapping("/{postId}")
	public ResponseEntity<ApiResult<Void>> update(
            @PathVariable Long postId,
            @RequestBody @Valid PostUpdateRequest request,
            HttpServletRequest servletRequest) {
        String authorization = servletRequest.getHeader("Authorization");
		postService.update(postId, request, authorization);
        return wrap(null);
	}

	@PatchMapping("/{postId}")
	public ResponseEntity<ApiResult<Void>> remove(@PathVariable Long postId) {
		postService.remove(postId);
        return wrap(null);
	}
}
