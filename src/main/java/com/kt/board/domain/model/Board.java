package com.kt.board.domain.model;

import com.kt.board.constants.BoardStatus;
import com.kt.board.domain.model.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Entity
@NoArgsConstructor(access = PROTECTED)
public class Board extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private BoardStatus status;

    protected Board(String name) {
        this.name = name;
        this.status = BoardStatus.ENABLED;
    }

    public static Board create(String name) {
        return new Board(name);
    }

		public void update(String name, BoardStatus status) {
			this.name = name;
			this.status = status;
		}

}
