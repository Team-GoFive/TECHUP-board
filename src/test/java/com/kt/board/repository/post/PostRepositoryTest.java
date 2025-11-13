package com.kt.board.repository.post;

import static com.kt.board.BoardGenerator.*;
import static com.kt.board.PostGenerator.*;
import static com.kt.board.UserGenerator.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import com.kt.board.domain.entity.BoardEntity;
import com.kt.board.domain.entity.PostEntity;
import com.kt.board.domain.entity.UserEntity;
import com.kt.board.repository.BoardRepository;
import com.kt.board.repository.PostRepository;
import com.kt.board.repository.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
class PostRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private PostRepository postRepository;

	@Test
	void 게시글_생성_실패__board_and_user_null() {
		// given
		PostEntity post = generatePost();
		// when

		// then
		assertThrowsExactly(
			DataIntegrityViolationException.class, () -> {
				postRepository.save(post);
			}
		);
	}

	@Test
	void 게시글_생성_실패__board_null() {
		// given
		UserEntity user = generateMemberUser();
		userRepository.save(user);
		PostEntity post = generatePost(user);
		// when

		// then
		assertThrowsExactly(
			DataIntegrityViolationException.class, () -> {
				postRepository.save(post);
			}
		);
	}

	@Test
	void 게시글_생성_실패__user_null() {
		// given
		UserEntity user = generateMemberUser();
		userRepository.save(user);
		BoardEntity board = generateBoard(user);
		boardRepository.save(board);
		PostEntity post = generatePost(board);
		// when

		// then
		assertThrowsExactly(
			DataIntegrityViolationException.class, () -> {
				postRepository.save(post);
			}
		);
	}

	@Test
	void 게시글_생성_성공() {
		// given
		UserEntity user = generateMemberUser();
		userRepository.save(user);
		BoardEntity board = generateBoard(user);
		boardRepository.save(board);
		PostEntity post = generatePost(board, user);
		// when
		PostEntity saved = postRepository.save(post);
		// then
		Optional<PostEntity> foundPost = postRepository.findById(saved.getId());
		assertThat(foundPost).isPresent();
		assertThat(foundPost.get().getTitle()).isEqualTo(post.getTitle());
	}
}