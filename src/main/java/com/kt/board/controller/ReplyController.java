package com.kt.board.controller;

import com.kt.board.common.api.ApiResult;
import com.kt.board.domain.dto.request.ReplyRequest;
import com.kt.board.service.ReplyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kt.board.common.api.ApiResult.wrap;

@RequiredArgsConstructor
@RequestMapping("/api/replies")
@RestController
public class ReplyController {

	private final ReplyService replyService;

	@PutMapping("/{replyId}")
	public ResponseEntity<ApiResult<Void>> update(
		@PathVariable Long replyId,
		@RequestBody @Valid ReplyRequest.Update request
	) {
		replyService.update(replyId, request);
		return wrap(null);
	}

	@DeleteMapping("/{replyId}")
	public ResponseEntity<ApiResult<Void>> remove(
		@PathVariable Long replyId
	) {
		replyService.remove(replyId);
		return wrap(null);
	}

}
