package com.kt.board.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kt.board.domain.dto.request.UserCreateRequest;
import com.kt.board.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "유저", description = "유저 관련 API")
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

	private final UserService userService;

	@Operation(summary = "회원가입 API",
		responses = {
			@ApiResponse(
				responseCode = "200",
				description = "회원가입 성공"
			),
			@ApiResponse(
				responseCode = "500",
				description = "백엔드에 문의 바랍니다."
			)
		}
	)
	@PostMapping
	@ResponseStatus(HttpStatus.OK)
	public void create(@RequestBody UserCreateRequest request) {
		userService.create(request);
	}
}
