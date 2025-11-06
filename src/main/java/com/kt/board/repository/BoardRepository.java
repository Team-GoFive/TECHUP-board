package com.kt.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.board.domain.model.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
