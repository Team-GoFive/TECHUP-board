package com.kt.board.common.api;

import com.kt.board.constants.message.ErrorCode;
import com.kt.board.exception.CustomException;

public class Preconditions {
	public static void validate(boolean expression, ErrorCode errorCode) {
		if (!expression) {
			throw new CustomException(errorCode);
		}
	}
}
