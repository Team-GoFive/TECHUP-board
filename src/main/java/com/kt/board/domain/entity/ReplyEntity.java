package com.kt.board.domain.entity;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;

import com.kt.board.constants.ReplyStatus;
import com.kt.board.constants.message.ErrorCode;
import com.kt.board.domain.entity.common.BaseCreatedByEntity;
import com.kt.board.exception.CustomException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyEntity extends BaseCreatedByEntity {

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private ReplyStatus status;

	@ManyToOne
	@JoinColumn(name = "parentBoard", nullable = false)
	private PostEntity parentPost;

	public void update(String content) {
		if ( !StringUtils.hasText(content) ) throw new CustomException(ErrorCode.REPLY_CONTENT_REQUIRED);
		this.content = content;
	}

	public void updateStatus(ReplyStatus status) {

		this.status = status;
	}

	protected ReplyEntity(
		String content,
		PostEntity parentPost,
		UserEntity createdBy
	) {
		this.content = content;
		this.parentPost = parentPost;
		this.createdBy = createdBy;
		this.status = ReplyStatus.ENABLED;
	}

	public static ReplyEntity create(
		final String content,
		final PostEntity parentPost,
		final UserEntity createdBy
	) {
		if ( !StringUtils.hasText(content) ) throw new CustomException(ErrorCode.REPLY_CONTENT_REQUIRED);
		if ( parentPost == null ) throw new CustomException(ErrorCode.POST_NOT_FOUND);
		if ( createdBy == null ) throw new CustomException(ErrorCode.USER_NOT_FOUND);
		return new ReplyEntity(content, parentPost, createdBy);
	}

}
