package org.petdians.user.animal.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.petdians.user.animal.entity.ImageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Log4j2
public class ImageRepositoryTests {

    @Autowired
    private ImageRepository imageRepository;


//    @Query("select image from ImageVO image")
    @Test
    public void testGetAllImage() {

        List<ImageVO> imageVOList = imageRepository.getAllImage(); //Rescue = 0, List<ImageVO>
        for(int i = 0; i<10; i++){

            log.info(imageVOList.get(i));

        }

    }
//    @Query("select image from ImageVO image " +
//            "inner join image.missingAnimalVO animal " +
//            "where image.missingAnimalVO = (select animal from animal where animal.rescueStatus = 0)")

    @Test
    public void testGetRescueImage(){

        List<ImageVO> imageVOList = imageRepository.getRescueImage(); //Rescue = 0, List<ImageVO>
        for(int i = 0; i<10; i++){

            log.info(imageVOList.get(i));

        }

    }

}
