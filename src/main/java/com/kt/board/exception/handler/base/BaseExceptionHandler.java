package com.kt.board.exception.handler.base;

import com.kt.board.exception.handler.SystemExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.kt.board.common.api.ApiErrorResponse;
import com.kt.board.exception.BaseException;

public class BaseExceptionHandler {
	public ResponseEntity<ApiErrorResponse> toResponse(BaseException ex) {
		HttpStatus status = ex.error().getStatus();
		String message = ex.error().getMessage();
		return toResponse(status, message);
	}

	public ResponseEntity<ApiErrorResponse> toResponse(HttpStatus status, String message) {
		return ApiErrorResponse.error(status, message);
	}
}
