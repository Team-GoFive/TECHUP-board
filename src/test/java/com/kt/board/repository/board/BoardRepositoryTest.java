package com.kt.board.repository.board;

import com.kt.board.constants.Gender;
import com.kt.board.constants.UserRole;
import com.kt.board.domain.entity.BoardEntity;
import com.kt.board.domain.entity.UserEntity;
import com.kt.board.repository.BoardRepository;

import com.kt.board.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@ActiveProfiles("test")
public class BoardRepositoryTest {

	@Autowired
	private BoardRepository boardRepository;

	@Autowired
	private UserRepository userRepository;

	UserEntity user;

	@BeforeEach
	void init() {
		user = UserEntity.create(
			"황테스트",
			"1231231!",
			Gender.MALE,
			"test@email.com",
			20,
			UserRole.MEMBER
		);
		userRepository.save(user);
	}

	@Test
	void 게시판_저장() {
		BoardEntity board = BoardEntity.create(
			"테스트_게시판",
			user
		);
		boardRepository.save(board);
		System.out.println("board id : " + board.getId());
		assertNotNull(boardRepository.findById(board.getId()));
	}

}





