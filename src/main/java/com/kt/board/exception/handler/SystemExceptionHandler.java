package com.kt.board.exception.handler;

import com.kt.board.common.api.ApiErrorResponse;
import com.kt.board.constants.message.ErrorCode;
import com.kt.board.exception.FieldValidationException;
import com.kt.board.exception.handler.base.BaseExceptionHandler;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class SystemExceptionHandler extends BaseExceptionHandler {

	@ExceptionHandler(FieldValidationException.class)
	public ResponseEntity<ApiErrorResponse> handleFieldValid(FieldValidationException ex) {
		ErrorCode error = ErrorCode.INVALID_DOMAIN_FIELD;
		String errorMessage = error.format(ex.getErrorMessage());
		log.warn("[FieldValidationException] message = {}", errorMessage);
		return toResponse(ex.error().getStatus(), errorMessage);
	};
}
