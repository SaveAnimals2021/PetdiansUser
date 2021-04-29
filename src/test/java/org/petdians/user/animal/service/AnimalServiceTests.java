package org.petdians.user.animal.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.petdians.user.animal.dto.MissingAnimalDTO;
import org.petdians.user.animal.entity.MissingAnimalVO;
import org.petdians.user.common.dto.PageRequestDTO;
import org.petdians.user.common.dto.PageResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootTest
@Log4j2
public class AnimalServiceTests {

    @Autowired
    private AnimalService service;

    @Test
    public void testGetAnimalList() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("animalNumber").descending());

        Page<Object[]> result = service.getAnimalList(pageable);

        result.getContent().forEach(objects -> log.info(Arrays.toString(objects)));

    }

    @Test
    public void testGetList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        pageRequestDTO.setPage(6);

        PageResultDTO<MissingAnimalDTO, Object[]> result = service.getList(pageRequestDTO);

        result.getDtoList().forEach(r->log.info(r));
    }

    @Test
    public void testRegister() {

        String dateStr = "2021-04-15 15";

        MissingAnimalDTO missingAnimalDTO = MissingAnimalDTO.builder()
                .type("test")
                .name("test")
                .sex("test")
                .age("test")
                .special("test")
                .color("test")
                .missingDate(dateStr)
                .missingLocation("test")
                .phoneNumber("test")
                .guardianName("test")
                .animalCode("test")
                .rescueStatus(0)
                .build();

        Integer result = service.register(missingAnimalDTO);

        log.info(result);

    }

}
