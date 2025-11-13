package com.kt.board.domain.entity;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import com.kt.board.constants.ReplyStatus;
import com.kt.board.exception.CustomException;

class ReplyDomainTest {

	ReplyEntity reply;
	PostEntity postEntity;
	UserEntity userEntity;

	@BeforeEach
	void setUp() throws Exception {
		postEntity = new PostEntity();
		userEntity = new UserEntity();
	}

	@Test
	void 댓글_생성_성공() {
		reply = ReplyEntity.create(
			"테스트내용",
			postEntity,
			userEntity
		);
		assertThat(reply.getContent()).isEqualTo("테스트내용");
		assertThat(reply.getParentPost()).isEqualTo(postEntity);
		assertThat(reply.getCreatedBy()).isEqualTo(userEntity);
		assertThat(reply.getStatus()).isEqualTo(ReplyStatus.ENABLED);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void 댓글_생성_실패__내용_null_이거나_공백(String content){
		assertThrows(
			CustomException.class,
			() -> ReplyEntity.create(
				content,
				postEntity,
				userEntity
			)
		);
	}

	@ParameterizedTest
	@NullSource
	void 댓글_생성_실패__사용자_null(UserEntity user) {
		assertThrows(
			CustomException.class,
			() -> ReplyEntity.create(
			"테스트내용",
			postEntity,
			user
			)
		);
	}

	@ParameterizedTest
	@NullSource
	void 댓글_생성_실패__게시판_null(PostEntity post) {
		assertThrows(
			CustomException.class,
			() -> ReplyEntity.create(
				"테스트내용",
				post,
				userEntity
			)
		);
	}

	@Test
	void 댓글_수정_성공() {
		reply = ReplyEntity.create(
			"테스트내용",
			postEntity,
			userEntity
		);
		reply.update("테스트수정");
		assertThat(reply.getContent()).isEqualTo("테스트수정");
	}

	@ParameterizedTest
	@NullAndEmptySource
	void 댓글_수정_실패__내용_null_이거나_공백(String content){
		reply = ReplyEntity.create(
			"테스트내용",
			postEntity,
			userEntity
		);
		assertThrows(
			CustomException.class,
			() -> reply.update(content)
		);
	}

	@Test
	void 댓글상태_변경_성공() {
		reply = ReplyEntity.create(
			"테스트내용",
			postEntity,
			userEntity
		);
		reply.updateStatus(ReplyStatus.DISABLED);
		assertThat(reply.getStatus()).isEqualTo(ReplyStatus.DISABLED);
	}

}