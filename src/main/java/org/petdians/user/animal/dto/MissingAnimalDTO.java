package org.petdians.user.animal.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

enum RescueStatus{



}

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class MissingAnimalDTO {

    private Integer animalNumber;

    private String animalCode;
    private String serviceName;

    private String type;
    private String name;

    @Builder.Default
    private String species = "모름"; // 진도
    private String sex; // 암컷
    private String age; // 2살
    private String special;
    @Builder.Default
    private String color = "미기재";

    @Builder.Default
    private List<ImageDTO> imageDTOList = new ArrayList<>();
    private String imageType;

    // redirect할수 있는 원래 사이트
    private String originURL;

    private String missingDate;
    private String missingLocation;

    private String rescueDate;
    private String rescueLocation;

    private String phoneNumber;
    private String guardianName;
    private Integer bno;

    private String regDate;
    private String updateDate;
    // 상태
    private Integer rescueStatus;
    private String situation;

    public String findMissingLocation(){
        String result = missingLocation;

        result.replace("인근", "").replace("부근", "");
        result.replace(",", " ");


        return result;
    }
    private Integer isCompleted;

    public String getRescueStatusString(){
        String result = "";
        if(null != rescueStatus){

            switch(rescueStatus){
                case 0:
                    result = "실종";
                    break;
                case 1:
                    result = "목격";
                    break;
                case 2:
                    result = "구조";
                    break;
                case 3:
                    result = "입양대기";
                    break;
                default:
                    result = "완료";
                    break;
            }
        }

        return result;
    }

}
