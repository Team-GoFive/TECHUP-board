package com.kt.board.exception;

import lombok.experimental.Accessors;

import org.springframework.core.NestedRuntimeException;

import com.kt.board.constants.message.ErrorCode;

import lombok.Getter;

@Getter
public class BaseException extends NestedRuntimeException {

	@Accessors(fluent = true)
	private ErrorCode error;

	public BaseException(ErrorCode error) {
		super(error.name());
		this.error = error;
	}

}
