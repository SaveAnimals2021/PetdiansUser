package org.petdians.user.member.entity;

import lombok.*;
import org.petdians.user.common.entity.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(value={AuditingEntityListener.class})
@Entity
@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_member")
public class Member{

    @Id
    private String memberID;

    private String memberPW;
    private String memberName;
    private String memberEmail;
    private String memberPhone;

    @CreatedDate
    @Column(name="regdate", updatable=false)
    protected LocalDateTime regdate;


    @LastModifiedDate
    @Column(name="updatedate")
    protected LocalDateTime updatedate;
}
