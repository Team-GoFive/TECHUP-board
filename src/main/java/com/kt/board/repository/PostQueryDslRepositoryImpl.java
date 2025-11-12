package com.kt.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.kt.board.domain.dto.response.PostResponse;
import com.kt.board.domain.dto.response.QPostResponse_Search;
import com.kt.board.domain.entity.QPostEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PostQueryDslRepositoryImpl implements PostQueryDslRepository {
	private final JPAQueryFactory jpaQueryFactory;
	private final QPostEntity post = QPostEntity.postEntity;

	@Override
	public Page<PostResponse.Search> search(Pageable pageable, String keyword) {

		var booleanBuilder = new BooleanBuilder();
		booleanBuilder.and(containsTitleOrContent(keyword));

		var content = jpaQueryFactory
			.select(new QPostResponse_Search(
				post.id,
				post.title,
				post.content
			))
			.from(post)
			.where(booleanBuilder)
			.orderBy(post.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		var total = jpaQueryFactory
			.select(post.id)
			.from(post)
			.where(booleanBuilder)
			.fetch()
			.size();

		return new PageImpl<>(content, pageable, total);
	}

	private BooleanExpression containsTitleOrContent(String keyword) {
		if (keyword == null || keyword.isEmpty()) {
			return null;
		}
		return post.title.containsIgnoreCase(keyword)
			.or(post.content.containsIgnoreCase(keyword));
	}
}
