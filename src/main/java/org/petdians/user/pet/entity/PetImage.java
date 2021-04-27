package org.petdians.user.pet.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@EntityListeners(value={AuditingEntityListener.class})
@Entity
@ToString(exclude = "pet")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tbl_petimage")
public class PetImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pino;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pet pet;

    private String uuid;
    private String uploadPath;
    private String fileName;


    private Boolean isMain;

    @CreatedDate
    @Column(name="regDate", updatable=false)
    protected LocalDateTime regDate;

    @LastModifiedDate
    protected LocalDateTime updateDate;

    public void setMain(){
        isMain = true;
    }
}
