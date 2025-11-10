package com.kt.board.controller;

import com.kt.board.common.api.ApiResult;
import com.kt.board.domain.dto.request.LoginRequest;
import com.kt.board.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kt.board.common.api.ApiResult.wrap;

@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResult<String>> login(@RequestBody LoginRequest request) {
        return wrap(authService.login(request));
    }
}
