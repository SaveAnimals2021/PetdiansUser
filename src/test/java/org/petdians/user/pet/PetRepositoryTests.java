package org.petdians.user.pet;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.petdians.user.member.entity.Member;
import org.petdians.user.member.repository.MemberRepository;
import org.petdians.user.pet.entity.Pet;
import org.petdians.user.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class PetRepositoryTests {

    @Autowired
    PetRepository petRepository;

    @Test
    public void testFindID(){
        Optional<Pet> pet = petRepository.findById(2L);

        log.info( pet.get() );

    }
}
