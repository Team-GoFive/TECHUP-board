package com.kt.board.service;

import com.kt.board.config.RedisConfig;
import com.kt.board.redis.TokenStore;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.board.domain.dto.request.PostCreateRequest;
import com.kt.board.domain.dto.request.PostUpdateRequest;
import com.kt.board.domain.entity.BoardEntity;
import com.kt.board.domain.entity.UserEntity;
import com.kt.board.domain.entity.PostEntity;
import com.kt.board.repository.BoardRepository;
import com.kt.board.repository.PostRepository;
import com.kt.board.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
	private final PostRepository postRepository;
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
    private final TokenStore tokenStore;

	@Transactional
	@Override
	public void create(Long boardId, PostCreateRequest request) {
		BoardEntity parentBoard = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다."));

		UserEntity createdBy = userRepository.findById(request.userId())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

		PostEntity postEntity = PostEntity.create(
			request.title(),
			request.content(),
			request.disclosureType(),
            parentBoard,
            createdBy
		);
		postRepository.save(postEntity);
	}

	@Transactional
	@Override
	public void update(Long postId, PostUpdateRequest request, String authorization){
		PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        post.update(request.title(), request.content(), request.disclosureType());
	}

	@Transactional
	@Override
	public void remove(Long postId){
		PostEntity postEntity = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
		postRepository.delete(postEntity); // soft delete
	}
}
