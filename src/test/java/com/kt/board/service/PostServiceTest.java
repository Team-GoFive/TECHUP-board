package com.kt.board.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kt.board.constants.Gender;
import com.kt.board.constants.PostDisclosureType;
import com.kt.board.constants.UserRole;
import com.kt.board.domain.dto.request.PostRequest;
import com.kt.board.domain.entity.BoardEntity;
import com.kt.board.domain.entity.PostEntity;
import com.kt.board.domain.entity.UserEntity;
import com.kt.board.exception.CustomException;
import com.kt.board.repository.BoardRepository;
import com.kt.board.repository.PostRepository;
import com.kt.board.repository.UserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostServiceTest {

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

	private UserEntity createUser() {
		return userRepository.save(
			UserEntity.create(
				"테스트",
				"테스트",
				Gender.MALE,
				"example@com",
				1,
				UserRole.MEMBER
			));
	}

	private BoardEntity createBoard(UserEntity userEntity) {
		return boardRepository.save(
			BoardEntity.create(
				"테스트게시판",
				userEntity
			));
	}

	private PostEntity createPost(
		UserEntity userEntity,
		BoardEntity boardEntity
	) {
		return postRepository.save(
			PostEntity.create(
				"title",
				"content",
				PostDisclosureType.PUBLIC,
				boardEntity,
				userEntity
			)
		);
	}

	@Test
	@DisplayName("게시글 생성")
	void 게시글_생성() {
		// given
		UserEntity user = createUser();
		BoardEntity board = createBoard(user);
		// when
		postService.create(
			board.getId(),
			new PostRequest.Create(
				"title",
				"content",
				PostDisclosureType.PUBLIC,
				user.getId()
			)
		);
		// then
		Optional<PostEntity> first = postRepository.findAll().stream().findFirst();

		assertThat(first.isPresent());
		assertThat(first.get().getTitle()).isEqualTo("title");

	}

	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("게시글 생성 실패 제목 공백")
	void 게시글_생성_실패__제목_null_공백(String title) {
		UserEntity user = createUser();
		BoardEntity board = createBoard(user);

		assertThrowsExactly(
			CustomException.class,
			() -> postService.create(
				board.getId(),
				new PostRequest.Create(
					title,
					"content",
					PostDisclosureType.PUBLIC,
					user.getId()
				)
			)
		);
	}

	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("게시글 생성 실패 내용 공백")
	void 게시글_생성_실패__내용_null_공백(String content) {
		UserEntity user = createUser();
		BoardEntity board = createBoard(user);

		assertThrowsExactly(
			CustomException.class,
			() -> postService.create(
				board.getId(),
				new PostRequest.Create(
					"title",
					content,
					PostDisclosureType.PUBLIC,
					user.getId()
				)
			)
		);
	}

	@Test
	@DisplayName("게시글 수정")
	void 게시글_내용_수정() {
		// given
		UserEntity user = createUser();
		BoardEntity board = createBoard(user);

		Long savedPostId = postService.create(
			board.getId(),
			new PostRequest.Create(
				"title",
				"content",
				PostDisclosureType.PUBLIC,
				user.getId()
			)
		);

		// when
		postService.update(
			savedPostId,
			new PostRequest.Update(
				"title-a",
				"content-a",
				PostDisclosureType.PUBLIC
			)
		);

		// then
		PostEntity post = postRepository.findById(savedPostId).orElseThrow();
		assertThat(post.getContent()).isEqualTo("content-a");
		assertThat(post.getTitle()).isEqualTo("title-a");
	}

	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("게시글 수정️ 실패 제목 공백")
	void 게시글_수정_실패__제목_공백(String title) {
		// given
		UserEntity user = createUser();
		BoardEntity board = createBoard(user);

		Long savedPostId = postService.create(
			board.getId(),
			new PostRequest.Create(
				"title",
				"content",
				PostDisclosureType.PUBLIC,
				user.getId()
			)
		);

		// then
		postRepository.findById(savedPostId).orElseThrow();
		assertThrowsExactly(
			CustomException.class, () ->
				postService.update(
					savedPostId,
					new PostRequest.Update(
						title,
						"content",
						PostDisclosureType.PUBLIC
					)
				)
		);
	}

	@ParameterizedTest
	@NullAndEmptySource
	@DisplayName("게시글 수정️ 실패 제목 공백")
	void 게시글_수정_실패__내용_공백(String content) {
		// given
		UserEntity user = createUser();
		BoardEntity board = createBoard(user);

		Long savedPostId = postService.create(
			board.getId(),
			new PostRequest.Create(
				"title",
				"content",
				PostDisclosureType.PUBLIC,
				user.getId()
			)
		);

		// then
		postRepository.findById(savedPostId).orElseThrow();
		assertThrowsExactly(
			CustomException.class, () ->
				postService.update(
					savedPostId,
					new PostRequest.Update(
						"title",
						content,
						PostDisclosureType.PUBLIC
					)
				)
		);
	}

	@Test
	@DisplayName("게시글 삭제")
	void 게시글_삭제() {

		// given
		UserEntity user = createUser();
		BoardEntity board = createBoard(user);
		PostEntity post = createPost(user, board);
		// when
		postService.remove(post.getId());
		// then
		assertThat(postRepository.findById(post.getId())).isEmpty();
	}

}