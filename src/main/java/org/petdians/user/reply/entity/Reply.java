package org.petdians.user.reply.entity;

import lombok.*;
import org.petdians.user.animal.entity.MissingAnimalVO;
import org.petdians.user.common.entity.BaseEntity;
import org.petdians.user.member.entity.Member;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"missingAnimalVO", "member"})
@Table(name="tbl_reply")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private MissingAnimalVO missingAnimalVO;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

}
