package com.kt.board.service.reply;

import com.kt.board.constants.Gender;
import com.kt.board.constants.PostDisclosureType;
import com.kt.board.constants.UserRole;
import com.kt.board.constants.message.ErrorCode;
import com.kt.board.domain.dto.request.ReplyRequest;
import com.kt.board.domain.entity.BoardEntity;
import com.kt.board.domain.entity.PostEntity;
import com.kt.board.domain.entity.ReplyEntity;
import com.kt.board.domain.entity.UserEntity;
import com.kt.board.exception.CustomException;
import com.kt.board.repository.BoardRepository;
import com.kt.board.repository.PostRepository;
import com.kt.board.repository.ReplyRepository;
import com.kt.board.repository.UserRepository;
import com.kt.board.service.ReplyService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class ReplyServiceCreateTest {

	@Autowired
	ReplyService replyService;

	@Autowired
	ReplyRepository replyRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BoardRepository boardRepository;

	@Autowired
	PostRepository postRepository;

	UserEntity user;
	PostEntity post;
	BoardEntity board;

	@BeforeEach
	void setUp() {
		user = userRepository.save(
			UserEntity.create(
				"테스터",
				"123123!@#",
				Gender.MALE,
				"test@gmail.com",
				26,
				UserRole.MEMBER
			)
		);

		board = boardRepository.save(
			BoardEntity.create(
				"테스트 게시판 이름",
				user
			)
		);

		post = postRepository.save(
			PostEntity.create(
				"테스트 게시글 제목",
				"테스트 게시글 내용",
				PostDisclosureType.PUBLIC,
				board,
				user
			)
		);
	}

	@Test
	@DisplayName("댓글_생성_성공")
	void 댓글_생성_성공() {
		ReplyRequest.Create request = new ReplyRequest.Create(
			"테스트 댓글 내용",
			user.getId()
		);

		replyService.create(post.getId(), request);

		ReplyEntity savedReply = replyRepository.findAll().getFirst();

		assertThat(savedReply.getContent()).isEqualTo("테스트 댓글 내용");
		assertThat(savedReply.getParentPost().getId()).isEqualTo(post.getId());
		assertThat(savedReply.getCreatedBy().getId()).isEqualTo(user.getId());
	}

	@Test
	@DisplayName("댓글_생성_실패__게시글없음")
	void 댓글_생성_실패__게시글없음() {
		ReplyRequest.Create request = new ReplyRequest.Create(
			"댓글 내용",
			user.getId()
		);

		CustomException ex = assertThrows(CustomException.class, () ->
			replyService.create(999999L, request)
		);

		assertThat(ex.getError()).isEqualTo(ErrorCode.POST_NOT_FOUND);
	}

	@Test
	@DisplayName("댓글_생성_실패__유저없음")
	void 댓글_생성_실패__유저없음() {
		ReplyRequest.Create request = new ReplyRequest.Create(
			"댓글 내용",
			999999L
		);

		CustomException ex = assertThrows(CustomException.class, () ->
			replyService.create(post.getId(), request)
		);

		assertThat(ex.getError()).isEqualTo(ErrorCode.USER_NOT_FOUND);
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"   "})
	@DisplayName("댓글_생성_실패__내용_null_공백")
	void 댓글_생성_실패__내용_null_공백(String content) {
		ReplyRequest.Create request = new ReplyRequest.Create(
			content,
			user.getId()
		);

		CustomException ex = assertThrows(CustomException.class, () ->
			replyService.create(post.getId(), request)
		);

		assertThat(ex.getError()).isEqualTo(ErrorCode.REPLY_EMPTY_CONTENT);
	}

}