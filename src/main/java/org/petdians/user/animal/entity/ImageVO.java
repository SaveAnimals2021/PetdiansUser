package org.petdians.user.animal.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Table(name="tbl_image")
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

    private Date regDate;
    private Date updateDate;

}
