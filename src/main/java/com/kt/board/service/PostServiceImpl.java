package com.kt.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kt.board.domain.dto.request.PostCreateRequest;
import com.kt.board.domain.dto.request.PostUpdateRequest;
import com.kt.board.domain.model.Board;
import com.kt.board.domain.model.User;
import com.kt.board.domain.model.post.Post;
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

	@Transactional
	@Override
	public void create(Long boardId, PostCreateRequest request) {
		Board board = boardRepository.findById(boardId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시판입니다."));

		User user = userRepository.findById(request.userId())
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

		Post post = Post.create(
			request.title(),
			request.content(),
			request.disclosureType(),
			board,
			user
		);
		postRepository.save(post);
	}

	@Transactional
	@Override
	public void update(Long postId, PostUpdateRequest request){
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
		post.update(request.title(), request.content(), request.disclosureType());
	}

	@Transactional
	@Override
	public void remove(Long postId){
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
		postRepository.delete(post); // soft delete
	}
}
