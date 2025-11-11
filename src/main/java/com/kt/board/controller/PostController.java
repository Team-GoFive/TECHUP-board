package com.kt.board.controller;

import com.kt.board.common.api.ApiResult;
import com.kt.board.domain.dto.request.PostRequest;
import com.kt.board.domain.dto.request.ReplyRequest;
import com.kt.board.service.ReplyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.kt.board.service.PostService;

import lombok.RequiredArgsConstructor;

import static com.kt.board.common.api.ApiResult.wrap;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

	private final PostService postService;
    private final ReplyService replyService;

	@PutMapping("/{postId}")
	public ResponseEntity<ApiResult<Void>> update(
            @PathVariable Long postId,
            @RequestBody @Valid PostRequest.Update request) {
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
            @RequestBody @Valid ReplyRequest.Create request) {
        replyService.create(postId, request);
        return wrap(null);
    }

}
