package com.kt.board.domain.model.post;

import com.kt.board.domain.model.Board;
import com.kt.board.domain.model.User;
import com.kt.board.domain.model.common.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Post extends BaseEntity {

	// 게시글 제목
	private String title;

	// 게시글 내용
	private String content;

	// 게시글 공개/비공개 여부
	@Enumerated(EnumType.STRING)
	private PostDisclosureType disclosureType;

	// Not null
	@ManyToOne
	private Board board;

	// Not null
	@ManyToOne
	private User user;

	public static Post create(String title, String content, PostDisclosureType disclosureType, Board board, User user) {
		return new Post(title, content, disclosureType, board, user);
	}
}
