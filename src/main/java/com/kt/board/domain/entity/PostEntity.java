package com.kt.board.domain.entity;

import org.apache.logging.log4j.util.Strings;

import com.kt.board.constants.PostDisclosureType;
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
@Entity(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity extends BaseCreatedByEntity {

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private PostDisclosureType disclosureType;

	@ManyToOne
	@JoinColumn(name = "parentBoard", nullable = false)
	private BoardEntity parentBoard;

	protected PostEntity(
		String title,
		String content,
		PostDisclosureType disclosureType,
		BoardEntity parentBoard,
		UserEntity createdBy
	) {
		this.title = title;
		this.content = content;
		this.disclosureType = disclosureType;
		this.parentBoard = parentBoard;
		this.createdBy = createdBy;
	}

	public static PostEntity create(
		String title,
		String content,
		PostDisclosureType disclosureType,
		BoardEntity parentBoard,
		UserEntity createdBy
	) {
		validateString(title, ErrorCode.POST_TITLE_IS_EMPTY);
		validateString(content, ErrorCode.POST_CONTENT_IS_EMPTY);
		return new PostEntity(
			title, content, disclosureType, parentBoard, createdBy
		);
	}

	public static void validateString(
		String data,
		ErrorCode errorCode
	) {
		if (Strings.isBlank(data)) {
			throw new CustomException(errorCode);
		}
	}

	public void update(String title, String content, PostDisclosureType disclosureType) {
		validateString(title, ErrorCode.POST_TITLE_IS_EMPTY);
		validateString(content, ErrorCode.POST_CONTENT_IS_EMPTY);
		
		this.title = title;
		this.content = content;
		this.disclosureType = disclosureType;
	}

}
