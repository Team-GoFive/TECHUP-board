package com.kt.board.service;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kt.board.constants.Gender;
import com.kt.board.constants.PostDisclosureType;
import com.kt.board.constants.UserRole;
import com.kt.board.domain.dto.request.PostRequest;
import com.kt.board.domain.entity.BoardEntity;
import com.kt.board.domain.entity.PostEntity;
import com.kt.board.domain.entity.UserEntity;
import com.kt.board.repository.BoardRepository;
import com.kt.board.repository.PostRepository;
import com.kt.board.repository.UserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostServiceImplTest {

	@Autowired
	private PostService postService;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private UserRepository userRepository;

	@BeforeEach
	void setup() {
		postRepository.deleteAll();
		boardRepository.deleteAll();
		userRepository.deleteAll();
	}

	@Test
	void 게시글_생성() {
		// given
		UserEntity userEntity = userRepository.save(
			UserEntity.create(
				"테스트",
				"테스트",
				Gender.MALE,
				"example@com",
				1,
				UserRole.MEMBER
			));

		BoardEntity boardEntity = boardRepository.save(
			BoardEntity.create(
				"테스트게시판",
				userEntity
			));

		// when
		postService.create(
			boardEntity.getId(),
			new PostRequest.Create(
				"title",
				"content",
				PostDisclosureType.PUBLIC,
				userEntity.getId()
			)
		);
		// then
		Optional<PostEntity> first = postRepository.findAll().stream().findFirst();

		assertThat(first.isPresent());
		assertThat(first.get().getTitle()).isEqualTo("title");
	}

}