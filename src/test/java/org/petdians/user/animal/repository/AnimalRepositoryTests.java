package org.petdians.user.animal.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.petdians.user.animal.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

@SpringBootTest
@Log4j2
public class AnimalRepositoryTests {

    @Autowired
    private AnimalRepository animalRepository;

    @Test
    public void testGetAnimalWithReplyCount() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("animalnumber").descending());

        Page<Object> result = animalRepository.getAnimalWithReplyCount(pageable);

        result.get().forEach(arr -> log.info(arr));

    }

}
