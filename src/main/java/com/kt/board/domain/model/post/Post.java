package com.kt.board.domain.model.post;

import com.kt.board.constants.PostDisclosureType;
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

	private String title;

	private String content;

	@Enumerated(EnumType.STRING)
	private PostDisclosureType disclosureType;

	@ManyToOne
	private Board board;

	@ManyToOne
	private User user;

	public static Post create(String title, String content, PostDisclosureType disclosureType, Board board, User user) {
		return new Post(title, content, disclosureType, board, user);
	}

	public void update(String title, String content, PostDisclosureType disclosureType) {
		this.title = title;
		this.content = content;
		this.disclosureType = disclosureType;
	}

}
