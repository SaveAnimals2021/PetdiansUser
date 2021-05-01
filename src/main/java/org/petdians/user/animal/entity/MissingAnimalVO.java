package org.petdians.user.animal.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name="tbl_animal")
@EntityListeners(value={AuditingEntityListener.class})
public class MissingAnimalVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer animalNumber;

    private String animalCode;
    @Builder.Default
    private String serviceName = "펫디언즈";

    private String type;
    private String name;

    @Builder.Default
    private String species = "모름"; // 진도
    private String sex; // 암컷
    private String age; // 2살
    private String special;
    private String color;

    // redirect할수 있는 원래 사이트
    @Builder.Default
    private String originUrl = "petdians";

    @Builder.Default
    private Date missingDate = null;
    @Builder.Default
    private String missingLocation = null;

    @Builder.Default
    private Date rescueDate = null;
    @Builder.Default
    private String rescueLocation = null;

    private String phoneNumber;
    private String guardianName;

    @CreatedDate
    @Column(name="regdate", updatable=false)
    private Date regDate;


    @LastModifiedDate
    @Column(name="updatedate")
    private Date updateDate;

    // 상태
    @Builder.Default
    private Integer rescueStatus = 0;
    private String situation;
    @Builder.Default
    private Integer isCompleted = 0;

}
