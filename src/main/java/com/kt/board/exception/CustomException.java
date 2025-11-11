package com.kt.board.exception;

import com.kt.board.constants.message.ErrorCode;

import lombok.Getter;

@Getter
public class CustomException extends BaseException {

	public CustomException(ErrorCode errorCode) {
		super(errorCode);
	}

}
