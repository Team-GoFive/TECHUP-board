package com.kt.board.domain.entity.common;

import com.kt.board.domain.entity.UserEntity;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
public class BaseCreatedByEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false, updatable = false)
    protected UserEntity createdBy;
}
