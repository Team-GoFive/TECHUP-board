package com.kt.board.domain.model;

import com.kt.board.constants.Gender;
import com.kt.board.constants.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
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

    private User(Long id,
                 String name,
                 String password,
                 Gender gender,
                 String email,
                 int age,
                 UserRole role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.age = age;
        this.role = role;
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    public static User create(final Long id,
                              final String name,
                              final String password,
                              final Gender gender,
                              final String email,
                              final int age,
                              final UserRole role) {
        return new User(id, name, password, gender, email, age, role);
    }
}
