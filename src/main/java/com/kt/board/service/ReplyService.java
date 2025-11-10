package com.kt.board.service;

import com.kt.board.domain.dto.request.ReplyCreateRequest;

public interface ReplyService {

    void create(Long postId, ReplyCreateRequest request);
}
