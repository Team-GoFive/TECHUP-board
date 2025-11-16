package com.kt.board.exception;

import com.kt.board.constants.message.ErrorCode;

import lombok.Getter;

@Getter
public class FieldValidationException extends BaseException {

	private String errorMessage;

	public FieldValidationException(ErrorCode error) {
		super(error);
	}

	public FieldValidationException(ErrorCode error, String errorMessage) {
		super(error);
		this.errorMessage = errorMessage;
	}

}
