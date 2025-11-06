package com.kt.board.domain.model;

import com.kt.board.constants.Gender;
import com.kt.board.constants.UserRole;
import com.kt.board.domain.model.common.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String name;
    private String password;
    private Gender gender;
    private String email;
    private int age;
    private UserRole role;

    protected User(String name,
                   String password,
                   Gender gender,
                   String email,
                   int age,
                   UserRole role) {
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    public static User create(final String name,
                              final String password,
                              final Gender gender,
                              final String email,
                              final int age,
                              final UserRole role) {
        return new User(name, password, gender, email, age, role);
    }
}
