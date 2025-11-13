package com.kt.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.board.domain.entity.BoardEntity;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

	Optional<BoardEntity> findByName(String name);
}
