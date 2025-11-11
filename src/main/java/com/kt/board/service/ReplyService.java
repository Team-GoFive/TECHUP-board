package com.kt.board.service;

import com.kt.board.domain.dto.request.ReplyRequest;

public interface ReplyService {

    void create(Long postId, ReplyRequest.Create request);
}
