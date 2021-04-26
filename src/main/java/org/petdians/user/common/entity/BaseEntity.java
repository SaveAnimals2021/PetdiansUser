package org.petdians.user.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

<<<<<<< HEAD
@MappedSuperclass //Entity를 만들지 않고, 상속으로 쓸 것이다.
@EntityListeners(value = {AuditingEntityListener.class}) //Entity 객체가 만들어지면 자동으로 감지한다.
@Getter //Entity니까 Getter
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "regdate", updatable = false) //컬럼명, 업데이트 여부
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
=======
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
>>>>>>> 61cbb26a520261307debd1f63c735991e7c2f91d

}
