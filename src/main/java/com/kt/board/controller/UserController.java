package com.kt.board.controller;

import com.kt.board.domain.dto.request.UserCreateRequest;
import com.kt.board.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "유저", description = "유저 API")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "유효성 검사 실패"),
        @ApiResponse(responseCode = "500", description = "서버 에러 - 백엔드에 바로 문의 바랍니다.")
})
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 생성")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody UserCreateRequest request) {
        userService.create(request);
    }
}
