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

@SpringBootTest
@Log4j2
public class AnimalRepositoryTests {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Test
    public void testGetAnimalWithReplyCount() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("animalnumber").descending());

        Page<Object[]> result = animalRepository.getAnimalWithReplyCount(pageable);

        result.get().forEach(arr -> log.info(arr));

    }

    @Test
    public void testInsert() throws ParseException {

//        private Integer animalNumber;
//        private String animalCode;
//        private String type;
//        private String name;
//        private String sex; // 암컷
//        private String age; // 2살
//        private String special;
//        private String color;
//        private Date missingDate;
//        private String missingLocation;
//        private String phoneNumber;
//        private String guardianName;
//
//        @CreatedDate
//        @Column(name="regdate", updatable=false)
//        protected LocalDateTime regdate;

//        @LastModifiedDate
//        @Column(name="updatedate")
//        protected LocalDateTime updatedate;
//
//        // 상태
//        @Builder.Default
//        private Integer rescueStatus = 0;
//        private String situation;
//        @Builder.Default
//        private Integer isCompleted = 0;
//        @Builder.Default
//        private String serviceName = "펫디언즈";
//        // redirect할수 있는 원래 사이트
//        @Builder.Default
//        private String originUrl = "/petdians";
//        @Builder.Default
//        private String species = "모름"; // 진도
//        @Builder.Default
//        private Date rescueDate = null;
//        @Builder.Default
//        private String rescueLocation = null;
//        private Integer animalNumber;

//        private String animalCode;
//        private String type;
//        private String name;
//        private String sex; // 암컷
//        private String age; // 2살
//        private String special;
//        private String color;
//        private Date missingDate;
//        private String missingLocation;
//        private String phoneNumber;
//        private String guardianName;



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
