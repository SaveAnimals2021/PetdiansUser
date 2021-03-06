package org.petdians.user.pet.entity;

import lombok.*;
import org.petdians.user.common.entity.BaseEntity;
import org.petdians.user.member.entity.Member;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@EntityListeners(value={AuditingEntityListener.class})
@Entity
@ToString(exclude = "member")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long pno;

    String petname;

    String species;
    Integer sex;
    Boolean isNeutralized;
    String age;
    String type;



    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @CreatedDate
    @Column(name="regdate", updatable=false)
    protected LocalDateTime regdate;


    @LastModifiedDate
    @Column(name="updatedate")
    protected LocalDateTime updatedate;


}
