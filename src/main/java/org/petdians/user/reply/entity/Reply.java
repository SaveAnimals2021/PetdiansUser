package org.petdians.user.reply.entity;

import lombok.*;
import org.petdians.user.animal.entity.MissingAnimalInfo;
import org.petdians.user.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "animal")
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String text;

    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY) //연관관계를 걸면 toString에서 무조건 빼라 [Lazy, exclude]는 세트이다.
    private MissingAnimalInfo missingAnimalInfo;

}
