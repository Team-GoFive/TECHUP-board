package com.kt.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.board.domain.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
