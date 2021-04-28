package org.petdians.user.animal.dto;

import lombok.Builder;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

@Data
@Builder
public class ImageDTO {

    private Integer animalNumber;
    private String uuid;
    private String uploadPath;
    private String fileName;
    private String type;


    public String getURL() {

        try {
            return URLEncoder.encode(uploadPath+"\\"+fileName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";

    }

    public String getThumbnailURL() {

        try {
            return URLEncoder.encode(uploadPath+"\\"+ "s_" + fileName,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";

    }

}
