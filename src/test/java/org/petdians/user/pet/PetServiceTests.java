package org.petdians.user.pet;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.petdians.user.common.dto.PageRequestDTO;
import org.petdians.user.common.dto.PageResultDTO;
import org.petdians.user.pet.dto.PetDTO;
import org.petdians.user.pet.entity.Pet;
import org.petdians.user.pet.service.PetService;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
@Log4j2
public class PetServiceTests {

    @Autowired
    PetService petService;

    @Test
    public void testPageList(){

        PageRequestDTO pageDTO = PageRequestDTO.builder().build();
        log.info(pageDTO.getPage());

        pageDTO.getPageable(Sort.by("pno").descending());

        PageResultDTO<PetDTO, Object[]> result = petService.getList(pageDTO);

        result.getDtoList().forEach(petDTO -> {
            log.info(petDTO);
        });
    }


    @Test
    public void testGetPetsByMemberID(){
        List<PetDTO> result = petService.getPetsAndImagesByMemberID("mk");

        result.forEach(pet->{
            log.info(pet);
            log.info(pet.getPetImageDTOList());
        });
    }

    @Test
    public void testGetPet(){
        PetDTO dto = petService.getPet(311L);

        log.info(dto);
    }


}
