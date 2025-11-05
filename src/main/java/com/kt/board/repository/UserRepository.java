package com.kt.board.repository;

import com.kt.board.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
	private final JdbcTemplate jdbcTemplate;

	public void save(User user) {
		var sql = """
			INSERT INTO user (id,
			                  name,
			                  password,
			                  gender,
			                  email,
			                  age,
			                  role,
			                  created_at,
			                  updated_at)
			VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
			""";

		jdbcTemplate.update(
			sql,
			user.getId(),
			user.getName(),
			user.getPassword(),
			user.getGender().name(),
			user.getEmail(),
			user.getAge(),
			user.getRole().name(),
			user.getCreatedAt(),
			user.getUpdatedAt()
		);
	}

	public Long selectMaxId() {
		var sql = "SELECT MAX(id) FROM user";
		var maxId = jdbcTemplate.queryForObject(sql, Long.class);
		return maxId == null ? 1L : maxId;
	}
}