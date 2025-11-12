package com.kt.board.controller;

import static com.kt.board.common.api.ApiResult.*;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kt.board.common.api.ApiResult;
import com.kt.board.common.api.PageResponse;
import com.kt.board.common.api.Paging;
import com.kt.board.domain.dto.request.PostRequest;
import com.kt.board.domain.dto.request.ReplyRequest;
import com.kt.board.domain.dto.response.PostResponse;
import com.kt.board.service.PostService;
import com.kt.board.service.ReplyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

	private final PostService postService;
	private final ReplyService replyService;

	@PutMapping("/{postId}")
	public ResponseEntity<ApiResult<Void>> update(
		@PathVariable Long postId,
		@RequestBody @Valid PostRequest.Update request
	) {
		postService.update(postId, request);
		return wrap(null);
	}

	@PatchMapping("/{postId}")
	public ResponseEntity<ApiResult<Void>> remove(@PathVariable Long postId) {
		postService.remove(postId);
		return wrap(null);
	}

	@PostMapping("/{postId}/replies")
	public ResponseEntity<ApiResult<Void>> createReply(
		@PathVariable Long postId,
		@RequestBody @Valid ReplyRequest.Create request
	) {
		replyService.create(postId, request);
		return wrap(null);
	}

	@GetMapping
	public ResponseEntity<ApiResult<PageResponse<PostResponse.Search>>> getPosts(
		@RequestParam(required = false) String title,
		@RequestParam(required = false) String contents,
		@RequestParam(required = false) String all,
		@ParameterObject Paging paging
	) {
		var posts = postService.getPosts(title, contents, all, paging.toPageable());
		return page(posts);
	}

}
