package com.kt.board.repository;

import com.kt.board.constants.message.ErrorCode;
import com.kt.board.exception.CustomException;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kt.board.domain.entity.ReplyEntity;

import javax.swing.text.html.Option;

import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<ReplyEntity, Long> {

	default ReplyEntity findByIdOrThrow(Long id) {
		return findById(id).orElseThrow(
			() -> new CustomException(ErrorCode.REPLY_NOT_FOUND)
		);
	}

}
