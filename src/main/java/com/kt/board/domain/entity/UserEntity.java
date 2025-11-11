package com.kt.board.domain.entity;

import com.kt.board.constants.Gender;
import com.kt.board.constants.UserRole;
import com.kt.board.domain.entity.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Entity(name = "\"user\"")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    protected UserEntity(String name,
                         String encodedPassword,
                         Gender gender,
                         String email,
                         int age,
                         UserRole role) {
        this.name = name;
        this.password = encodedPassword;
        this.gender = gender;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    public static UserEntity create(final String name,
                                    final String encodedPassword,
                                    final Gender gender,
                                    final String email,
                                    final int age,
                                    final UserRole role) {
        return new UserEntity(name, encodedPassword, gender, email, age, role);
    }
}
