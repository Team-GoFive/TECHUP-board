package com.kt.board.domain.entity;

import static lombok.AccessLevel.*;

import com.kt.board.constants.BoardStatus;
import com.kt.board.constants.message.ErrorCode;
import com.kt.board.domain.entity.common.BaseCreatedByEntity;

import com.kt.board.exception.CustomException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.apache.logging.log4j.util.Strings;
import org.springframework.util.StringUtils;

@Getter
@Entity(name = "board")
@NoArgsConstructor(access = PROTECTED)
public class BoardEntity extends BaseCreatedByEntity {

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private BoardStatus status;

	protected BoardEntity(String name, UserEntity createdBy) {
		this.name = name;
		this.status = BoardStatus.ENABLED;
		this.createdBy = createdBy;
	}

	public static BoardEntity create(String name, UserEntity createdBy) {
		if (!StringUtils.hasText(name)) {
			throw new CustomException(ErrorCode.BOARD_NAME_REQUIRED);
		}
		return new BoardEntity(name, createdBy);
	}

	public void update(String name) {
		this.name = name;
	}

}