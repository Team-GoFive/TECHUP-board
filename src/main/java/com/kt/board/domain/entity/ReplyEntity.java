package com.kt.board.domain.entity;

import com.kt.board.constants.ReplyStatus;
import com.kt.board.domain.entity.common.BaseCreatedByEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "reply")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReplyEntity extends BaseCreatedByEntity {

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReplyStatus status;

    @ManyToOne
    @JoinColumn(name = "parentBoard", nullable = false)
    private PostEntity parentPost;

    protected ReplyEntity(String content,
                          PostEntity parentPost,
                          UserEntity createdBy) {
        this.content = content;
        this.parentPost = parentPost;
        this.createdBy = createdBy;
        this.status = ReplyStatus.ENABLED;
    }

    public static ReplyEntity create(final String content,
                                     final PostEntity parentPost,
                                     final UserEntity createdBy) {
        return new ReplyEntity(content, parentPost, createdBy);
    }

}
