package com.kt.board.repository;

import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import com.kt.board.constants.Gender;
import com.kt.board.constants.PostDisclosureType;
import com.kt.board.constants.UserRole;
import com.kt.board.domain.entity.BoardEntity;
import com.kt.board.domain.entity.PostEntity;
import com.kt.board.domain.entity.ReplyEntity;
import com.kt.board.domain.entity.UserEntity;
import com.kt.board.exception.CustomException;

// @Transactional

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ReplyRepositoryTest {

	@Autowired
	ReplyRepository replyRepository;
	@Autowired
	PostRepository postRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	BoardRepository boardRepository;

	UserEntity userEntity;
	PostEntity postEntity;

	@BeforeEach
	void setUp() throws Exception {
		userEntity = UserEntity.create(
			"테스터1",
			"test1234",
			Gender.MALE,
			"wjdtn747@naver.com",
			22,
			UserRole.MEMBER
		);
		userRepository.save(userEntity);

		BoardEntity board = BoardEntity.create(
			"테스트보드1",
			userEntity
		);
		boardRepository.save(board);

		postEntity = PostEntity.create(
			"제목1",
			"내용1",
			PostDisclosureType.PUBLIC,
			board,
			userEntity
		);
		postRepository.save(postEntity);
	}

	@Test
	void 댓글_저장_성공(){
		ReplyEntity sendedReplyEntity = replyRepository.save(
			ReplyEntity.create(
				"생성될댓글내용",
				postEntity,
				userEntity
			)
		);
		ReplyEntity retrievedReplyEntity = replyRepository.findByIdOrThrow(sendedReplyEntity.getId());
		assertThat(retrievedReplyEntity).isEqualTo(sendedReplyEntity);
	}

	@Test
	void 댓글_수정_성공(){
		ReplyEntity testReplyEntity = ReplyEntity.create(
			"수정될내용",
			postEntity,
			userEntity
		);
		replyRepository.save(testReplyEntity);
		testReplyEntity.update("수정한내용");

		ReplyEntity retrievedReplyEntity = replyRepository.findByIdOrThrow(testReplyEntity.getId());
		assertThat(retrievedReplyEntity.getContent()).isEqualTo(testReplyEntity.getContent());
	}

	@Test
	void 댓글_삭제_성공(){
		ReplyEntity testReplyEntity = ReplyEntity.create(
			"삭제될댓글",
			postEntity,
			userEntity
		);
		replyRepository.save(testReplyEntity);
		replyRepository.deleteById(testReplyEntity.getId());
		assertThrowsExactly(
			CustomException.class,
			() -> replyRepository.findByIdOrThrow(testReplyEntity.getId()));
	}
}