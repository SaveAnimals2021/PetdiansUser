package org.petdians.user.animal.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.petdians.user.common.dto.PageRequestDTO;
import org.petdians.user.common.dto.PageResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
@Log4j2
public class AnimalServiceTests {

    @Autowired
    private AnimalService service;

    @Test
    public void testGetAnimalList() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("animalNumber").descending());

        Page<Object> result = service.getAnimalList(pageable);

        result.get().forEach(o -> log.info(o));

    }

    @Test
    public void testGetList(){
        PageRequestDTO pageRequestDTO = new PageRequestDTO();

        PageResultDTO result = service.getList(pageRequestDTO);

        result.getDtoList().forEach(r->log.info(r));
    }

}
