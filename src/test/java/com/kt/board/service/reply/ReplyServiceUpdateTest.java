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
class ReplyServiceUpdateTest {

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
	BoardEntity board;
	PostEntity post;
	ReplyEntity reply;

	@BeforeEach
	void setUp() {

		user = userRepository.save(
			UserEntity.create(
				"테스터",
				"1234!@#",
				Gender.MALE,
				"update@test.com",
				26,
				UserRole.MEMBER
			)
		);

		board = boardRepository.save(
			BoardEntity.create(
				"수정 게시판",
				user
			)
		);

		post = postRepository.save(
			PostEntity.create(
				"수정 게시글 제목",
				"내용",
				PostDisclosureType.PUBLIC,
				board,
				user
			)
		);

		reply = replyRepository.save(
			ReplyEntity.create(
				"기존 댓글 내용",
				post,
				user
			)
		);
	}

	@Test
	@DisplayName("댓글_수정_성공")
	void 댓글_수정_성공() {
		ReplyRequest.Update request = new ReplyRequest.Update(
			"수정된 댓글 내용",
			user.getId()
		);

		replyService.update(reply.getId(), request);

		ReplyEntity updated = replyRepository.findById(reply.getId())
			.orElseThrow();

		assertThat(updated.getContent()).isEqualTo("수정된 댓글 내용");
	}

	@Test
	@DisplayName("댓글_수정_실패__replyId_없음")
	void 댓글_수정_실패__replyId_없음() {

		ReplyRequest.Update request = new ReplyRequest.Update(
			"수정 내용",
			user.getId()
		);

		CustomException ex = assertThrows(CustomException.class, () ->
			replyService.update(999999L, request)
		);

		assertThat(ex.getError()).isEqualTo(ErrorCode.REPLY_NOT_FOUND);
	}

	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = {"   "})
	@DisplayName("댓글_수정_실패__내용_null_공백")
	void 댓글_수정_실패__내용_null_공백(String content) {
		ReplyRequest.Update request = new ReplyRequest.Update(
			content,
			user.getId()
		);

		CustomException ex = assertThrows(CustomException.class, () ->
			replyService.update(reply.getId(), request)
		);

		assertThat(ex.getError()).isEqualTo(ErrorCode.REPLY_EMPTY_CONTENT);
	}
}