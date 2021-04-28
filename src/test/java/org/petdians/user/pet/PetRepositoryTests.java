package org.petdians.user.pet;


import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.petdians.user.member.entity.Member;
import org.petdians.user.member.repository.MemberRepository;
import org.petdians.user.pet.dto.PetDTO;
import org.petdians.user.pet.entity.Pet;
import org.petdians.user.pet.entity.PetImage;
import org.petdians.user.pet.repository.PetImageRepository;
import org.petdians.user.pet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class PetRepositoryTests {

    @Autowired
    PetRepository petRepository;
    @Autowired
    PetImageRepository petImageRepository;

    @Test
    public void testFindID(){
        Optional<Pet> pet = petRepository.findById(2L);

        log.info( pet.get() );

    }

    @Test
    @Transactional
    @Commit
    public void testInsertPets(){
        Integer age = (int)(Math.random()*10+1);

        IntStream.rangeClosed(1,3).forEach(i->{
            Pet pet = Pet.builder()
                    .petname("PET_"+i)
                    .sex(0)
                    .isNeutralized(false)
                    .age(age + "")
                    .type("강아지")
                    .member(Member.builder().memberID("mk").build())
                    .build();

            // movie 번호 생성됨
            petRepository.save(pet);

            log.info(pet.getPno());

            // 최대 5개의 영화 잋미지
            int count = (int)(Math.random() * 5) + 1;

            for(int j = 0; j < count; ++j){

                Boolean isMain = (j == 0);
                String uuid = UUID.randomUUID().toString();
                PetImage image = PetImage.builder()
                        .uuid(uuid)
                        .pet(pet)
                        .fileName("IMAGE_NAME_" + i)
                        .uploadPath("C:\\upload\\2021\\04\\26")
                        .isMain(isMain)
                        .build();

                petImageRepository.save(image);

            } // end for j

        }); // end for i
    }



    @Test
    public void testGetPagedList(){
        Pageable page = PageRequest.of(0,10, Sort.by("pno").descending());

        Page<Object[]> result = petRepository.getPagedList(page);

        result.getContent().forEach(arr->{
            log.info(Arrays.toString(arr));
        });
    }

    @Test
    public void testGetPetsByMember(){
        String memberID = "mk";

        Object[] result = petRepository.getPetsByMemberID(memberID);

        for(int i = 0; i < result.length; ++i){
            Object[] temp = (Object[])(result)[i];
            Pet tempPet = (Pet)temp[0];
            // Integer tempCount = (Integer)temp[1];

            // tempPet.setImageCount(tempCount);

            log.info( temp[0] );
            log.info( temp[1] );
        }
    }

    // PNO로 PetImage List로 가져오기
    @Test
    public void testGetPetImagesByPno(){
        Object[] result = petImageRepository.getPetImagesByPno(311L);

        for(int i = 0; i < result.length; ++i){


            PetImage temp = (PetImage)result[i];


            log.info( Arrays.toString(result) );
        }

        PetImage i1 = (PetImage)result[0];
        PetImage i2 = (PetImage)result[1];

        log.info(i1);
        log.info(i2);
    }

}
