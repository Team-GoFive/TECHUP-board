package com.kt.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kt.board.domain.model.post.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
