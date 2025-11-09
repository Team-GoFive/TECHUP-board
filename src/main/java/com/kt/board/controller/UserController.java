package com.kt.board.controller;

import com.kt.board.common.api.ApiResult;
import com.kt.board.domain.dto.request.UserCreateRequest;
import com.kt.board.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.kt.board.common.api.ApiResult.wrap;
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
