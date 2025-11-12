package com.kt.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.board.domain.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Long>, PostQueryDslRepository {
}
