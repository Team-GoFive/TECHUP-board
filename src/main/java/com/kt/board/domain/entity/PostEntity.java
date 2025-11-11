package com.kt.board.domain.entity;

import com.kt.board.constants.PostDisclosureType;
import com.kt.board.domain.entity.common.BaseCreatedByEntity;

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
		return new PostEntity(
			title, content, disclosureType, parentBoard, createdBy
		);
	}

	public void update(String title, String content, PostDisclosureType disclosureType) {
		this.title = title;
		this.content = content;
		this.disclosureType = disclosureType;
	}

}
