package com.kt.board.domain.post;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import com.kt.board.constants.PostDisclosureType;
import com.kt.board.domain.entity.PostEntity;
import com.kt.board.exception.CustomException;

class PostDomainTest {

	@Test
	void 게시글_생성_성공() {
		PostEntity post = PostEntity.create(
			"테스트 제목",
			"테스트 내용",
			PostDisclosureType.PUBLIC,
			null,
			null
		);
		assertThat(post.getTitle()).isEqualTo("테스트 제목");
		assertThat(post.getContent()).isEqualTo("테스트 내용");
	}

	@ParameterizedTest
	@NullAndEmptySource
	void 게시글_생성_실패__제목_blank(String title) {
		assertThrowsExactly(
			CustomException.class, () -> {
				PostEntity.create(
					title,
					"테스트 내용",
					PostDisclosureType.PUBLIC,
					null,
					null
				);
			}
		);
	}

	@Test
	void 게시글_생성_실패__공개_범위_null() {
		assertThrowsExactly(
			CustomException.class, () -> {
				PostEntity.create(
					"테스트 제목",
					"테스트 내용",
					null,
					null,
					null
				);
			}
		);
	}
}