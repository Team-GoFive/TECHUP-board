package com.kt.board.controller;

import com.kt.board.domain.dto.request.UserCreateRequest;
import com.kt.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
