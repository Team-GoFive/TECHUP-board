package com.kt.board.domain.entity.common;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(
		shape = JsonFormat.Shape.STRING,
		pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
		timezone = "Asia/Seoul"
	)
	protected Instant createdAt;

	@LastModifiedDate
	@Column(nullable = false)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	@JsonFormat(
		shape = JsonFormat.Shape.STRING,
		pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
		timezone = "Asia/Seoul"
	)
	protected Instant updatedAt;

}
