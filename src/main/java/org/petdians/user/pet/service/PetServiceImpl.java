package org.petdians.user.pet.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.petdians.user.common.dto.PageRequestDTO;
import org.petdians.user.common.dto.PageResultDTO;
import org.petdians.user.pet.dto.PetDTO;
import org.petdians.user.pet.dto.PetImageDTO;
import org.petdians.user.pet.entity.Pet;
import org.petdians.user.pet.entity.PetImage;
import org.petdians.user.pet.repository.PetImageRepository;
import org.petdians.user.pet.repository.PetRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


@Service
@Log4j2
@RequiredArgsConstructor
public class PetServiceImpl implements PetService{

    private final PetRepository petRepository;
    private final PetImageRepository petImageRepository;

    @Override
    public Long register(PetDTO dto) {

        Map<String, Object> entityMap = dtoToEntities(dto);
        Pet pet = (Pet) entityMap.get("pet");
        List<PetImage> movieImageList = (List<PetImage>) entityMap.get("imageList");

        // PET 저장
        petRepository.save(pet);

        // PET IMAGE 저장
        movieImageList.forEach(petImage -> {
            petImageRepository.save(petImage);
        });

        return pet.getPno();
    }

    @Override
    public PageResultDTO<PetDTO, Object[]> getList(PageRequestDTO requestDTO) {

        Pageable pageable = requestDTO.getPageable(Sort.by("pno").descending());

        Page<Object[]> result = petRepository.getPagedList(pageable);

        log.info("==============================================");
        result.getContent().forEach(arr -> {
            log.info(Arrays.toString(arr));
        });


        Function<Object[], PetDTO> fn = (arr -> entitiesToDTO(
                (Pet)arr[0] ,
                (List<PetImage>)(Arrays.asList(  (PetImage)arr[1])  )
        ));

        return new PageResultDTO<>(result, fn);
    }

    @Override
    public List<PetDTO> getPetsByMemberID(String memberid) {

        Object[] result = petRepository.getPetsByMemberID(memberid);

        List<PetDTO> resultList = new ArrayList<>();

        for(int i = 0; i < result.length; ++i){
            Object[] temp = (Object[])(result)[i];
            Pet tempPet = (Pet)temp[0];
            log.info(temp[1]);

            Long tempCount = (long)temp[1];

            resultList.add(entityToDTO(tempPet, tempCount));
        }

        return resultList;
    }

    @Override
    public List<PetDTO> getPetsAndImagesByMemberID(String memberid) {
        Object[] result = petRepository.getPetsAndImagesByMemberID(memberid);

        log.info("result.length : " + result.length);

        List<PetDTO> resultList = new ArrayList<>();

        for(int i = 0; i < result.length; ++i){


            Object[] arr = (Object[])result[i];

            Pet pet = (Pet)arr[0];
            PetImage petImage = (PetImage)arr[1];

            List<PetImage> list = new ArrayList<>();
            list.add(petImage);

            PetDTO petDTO = entitiesToDTO(pet, list);

            log.info("PetDTO: " + petDTO);

            resultList.add(petDTO);
        }

        return resultList;
    }

    @Override
    public PetDTO getPet(Long pno) {

        Object[] result = petRepository.getPetAndImages(pno);

        PetDTO petDTO = null;
        List<PetImageDTO> imageList = new ArrayList<>();

        for(int i = 0; i < result.length; ++i){
            log.info( Arrays.toString(result) );

            Object[] arr = (Object[])result[i];
            log.info(arr[0]);
            log.info(arr[1]);

            Pet pet = (Pet)arr[0];
            PetImage image = (PetImage)arr[1];

            if(null == petDTO){
                petDTO = entityToDTO(pet);
            }

            imageList.add(entityToImageDTO(image));

            log.info("============================");
        }

        if(null != petDTO){
            petDTO.setPetImageDTOList(imageList);
        }

        return petDTO;
    }


}
