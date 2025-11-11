package com.kt.board.constants.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SuccessCode {

	RESULT("ok", "성공");

	private final String code;
	private final String message;

}
