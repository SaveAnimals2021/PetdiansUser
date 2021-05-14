package org.petdians.user.animal.repository;

import lombok.Builder;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.petdians.user.animal.entity.MissingAnimalVO;
import org.petdians.user.animal.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.Column;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
@Log4j2
public class AnimalRepositoryTests {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Test
    public void testGetAnimalWithImageList() {

        Integer animalNumber = 11913;
        List<Object[]> result = animalRepository.getAnimalWithImageList(animalNumber);

        result.forEach(objects -> {

            log.info(Arrays.toString(objects));

        });

    }

    @Test
    public void testGetAnimalListWithImage() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("animalNumber").descending());

        Page<Object[]> result = animalRepository.getAnimalListWithImage(pageable);

        result.get().forEach(arr -> log.info(Arrays.toString(arr)));

    }

    @Test
    public void testInsert() throws ParseException {

        String dateStr = "2021-04-15 15";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH");

        MissingAnimalVO missingAnimalVO = MissingAnimalVO.builder()
                .type("test")
                .name("test")
                .sex("test")
                .age("test")
                .special("test")
                .color("test")
                .missingDate(simpleDateFormat.parse(dateStr))
                .missingLocation("test")
                .phoneNumber("test")
                .guardianName("test")
                .animalCode("test")
                .build();

        animalRepository.save(missingAnimalVO);

    }

}
