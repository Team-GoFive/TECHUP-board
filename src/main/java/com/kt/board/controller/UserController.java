package com.kt.board.controller;

import com.kt.board.domain.dto.request.UserCreateRequest;
import com.kt.board.service.UserService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ApiResponses(value = {
	@ApiResponse(responseCode="400", description = "유효성 검사 실패"),
	@ApiResponse(responseCode = "500", description = "서버 에러")
})
@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody UserCreateRequest request) {
        userService.create(request);
    }
}
