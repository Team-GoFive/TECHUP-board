package com.kt.board.domain.model;

import com.kt.board.constants.Gender;
import com.kt.board.constants.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String password;
    private Gender gender;
    private String email;
    private int age;
    private UserRole role;
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
