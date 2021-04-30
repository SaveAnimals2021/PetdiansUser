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
@Table(name="tbl_image")
@ToString(exclude = "missingAnimalVO")
@EntityListeners(value={AuditingEntityListener.class})
public class ImageVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ino;
    private String url;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animalNumber")
    private MissingAnimalVO missingAnimalVO;
    private String uuid;
    private String uploadPath;
    private String fileName;
    private String type;

    @CreatedDate
    @Column(name="regdate", updatable=false)
    private Date regDate;

    @LastModifiedDate
    @Column(name="updatedate")
    private Date updateDate;

}
