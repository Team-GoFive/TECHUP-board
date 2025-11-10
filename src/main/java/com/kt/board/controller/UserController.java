package com.kt.board.controller;

import com.kt.board.common.api.ApiResult;
import com.kt.board.domain.dto.request.UserCreateRequest;
import com.kt.board.service.UserService;
import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.kt.board.common.api.ApiResult.wrap;
@ApiResponses(value = {
	@ApiResponse(responseCode="400", description = "유효성 검사 실패"),
	@ApiResponse(responseCode = "500", description = "서버 에러")
})
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResult<Void>> create(@RequestBody @Valid UserCreateRequest request) {
        userService.create(request);
        return wrap(null);
    }
}
