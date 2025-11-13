package com.kt.board.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.kt.board.common.api.searchtype.PostSearchType;
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
	public Page<PostResponse.Search> search(String keyword, PostSearchType searchType, Pageable pageable) {

		BooleanBuilder booleanBuilder = new BooleanBuilder();
		booleanBuilder.and(containsKeywordBySearchType(keyword, searchType));

		List<PostResponse.Search> content = jpaQueryFactory
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

		int total = jpaQueryFactory
			.select(post.id)
			.from(post)
			.where(booleanBuilder)
			.fetch()
			.size();

		return new PageImpl<>(content, pageable, total);
	}

	private BooleanExpression containsKeywordBySearchType(String keyword, PostSearchType searchType) {
		if (keyword == null || keyword.isEmpty() || searchType == null) {
			return null;
		}
		return switch (searchType) {
			case TITLE -> post.title.containsIgnoreCase(keyword);
			case CONTENT -> post.content.containsIgnoreCase(keyword);
			case TITLE_OR_CONTENT -> post.title.containsIgnoreCase(keyword)
				.or(post.content.containsIgnoreCase(keyword));
		};
	}

}
