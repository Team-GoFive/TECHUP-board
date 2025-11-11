package com.kt.board.controller;

import static com.kt.board.common.api.ApiResult.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.board.common.api.ApiResult;
import com.kt.board.domain.dto.request.UserRequest;
import com.kt.board.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<ApiResult<Void>> create(
		@RequestBody @Valid UserRequest.Create request
	) {
		userService.create(request);
		return wrap(null);
	}
}
