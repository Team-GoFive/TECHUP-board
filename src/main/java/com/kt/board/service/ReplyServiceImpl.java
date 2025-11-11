package com.kt.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.board.constants.message.ErrorCode;
import com.kt.board.domain.dto.request.ReplyRequest;
import com.kt.board.domain.entity.PostEntity;
import com.kt.board.domain.entity.ReplyEntity;
import com.kt.board.domain.entity.UserEntity;
import com.kt.board.exception.CustomException;
import com.kt.board.repository.PostRepository;
import com.kt.board.repository.ReplyRepository;
import com.kt.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {

	private final PostRepository postRepository;
	private final ReplyRepository replyRepository;
	private final UserRepository userRepository;

	@Override
	@Transactional
	public void create(Long postId, ReplyRequest.Create request) {
		PostEntity parentPost = postRepository.findById(postId).orElseThrow(
			() -> new CustomException(ErrorCode.POST_NOT_FOUND)
		);

		UserEntity createdBy = userRepository.findById(request.userId()).orElseThrow(
			() -> new CustomException(ErrorCode.USER_NOT_FOUND)
		);

		ReplyEntity reply = ReplyEntity.create(
			request.content(),
			parentPost,
			createdBy
		);
		replyRepository.save(reply);
	}
}
