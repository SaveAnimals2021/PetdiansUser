package org.petdians.user.pet.dto;

import lombok.*;
import org.petdians.user.member.entity.Member;

import java.time.LocalDateTime;
import java.util.*;

@ToString(exclude = "member")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {

    Long pno;

    String petname;

    String species;
    Integer sex;
    Boolean isNeutralized;
    String age;
    String type;

    Member member;

    Long imageCount;

    @Builder.Default
    List<PetImageDTO> petImageDTOList = new ArrayList<PetImageDTO>();

    private LocalDateTime regDate;
    private LocalDateTime updateDate;
}
