package org.petdians.user.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass // entity로 등록되지 않는다 = table을 생성 X
@EntityListeners(value={AuditingEntityListener.class})
@Getter
public abstract class BaseEntity {
    @CreatedDate
    @Column(name="regdate", updatable=false)
    protected LocalDateTime regDate;

    @LastModifiedDate
    @Column(name="updatedate")
    protected LocalDateTime modDate;

}