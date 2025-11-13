package com.kt.board.repository.post;

import static com.kt.board.PostGenerator.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.kt.board.repository.BoardRepository;
import com.kt.board.repository.PostRepository;
import com.kt.board.repository.UserRepository;

@DataJpaTest
class PostRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private PostRepository postRepository;

	@Test
	void 게시글_생성_실패__() {
		// given
		// UserEntity user = UserGenerator.generateMemberUser();
		// userRepository.save(user);
		// BoardEntity board = BoardGenerator.generateBoard(user);
		// boardRepository.save(board);
		//
		// // when
		// String title = "title_" + UUID.randomUUID();
		// PostEntity post = generatePost(title, board, user);

		// then
		postRepository.save(generatePost());

	}

}