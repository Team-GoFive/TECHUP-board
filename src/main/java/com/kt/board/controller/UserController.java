package com.kt.board.controller;

import com.kt.board.domain.dto.request.UserCreateRequest;
import com.kt.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

// 클래스 레벨에 API 그룹 설정
@Tag(name = "User API", description = "사용자 계정 생성 및 관리 API")
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

		@Operation(summary = "새로운 사용자 계정 생성",
			description = "새로운 사용자를 등록합니다.")

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody UserCreateRequest request) {
        userService.create(request);
    }
}
