package org.petdians.user.pet.service;

import org.petdians.user.common.dto.PageRequestDTO;
import org.petdians.user.common.dto.PageResultDTO;
import org.petdians.user.pet.dto.PetDTO;
import org.petdians.user.pet.dto.PetImageDTO;
import org.petdians.user.pet.entity.Pet;
import org.petdians.user.pet.entity.PetImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface PetService {

    Long register(PetDTO dto);

    PageResultDTO<PetDTO, Object[]> getList(PageRequestDTO requestDTO);

    List<PetDTO> getPetsByMemberID(String memberid);

    List<PetDTO> getPetsAndImagesByMemberID(String memberid);

    PetDTO getPet(Long pno);

    default PetImageDTO entityToImageDTO(PetImage image){
        return PetImageDTO.builder().fileName(image.getFileName())
                .pino(image.getPino())
                .regDate(image.getRegDate())
                .uploadPath(image.getUploadPath())
                .uuid(image.getUuid())
                .pino(image.getPino())
                .updateDate(image.getUpdateDate())
                .build();
    }

    default PetDTO entityToDTO(Pet pet, Long imageCount){
        return PetDTO.builder()
                .pno(pet.getPno())
                .petname(pet.getPetname())
                .regDate(pet.getRegdate())
                .updateDate(pet.getUpdatedate())
                .age(pet.getAge())
                .sex(pet.getSex())
                .member(pet.getMember())
                .isNeutralized(pet.getIsNeutralized())
                .species(pet.getSpecies())
                .type(pet.getType())
                .imageCount(imageCount)
                .build();
    }

    default PetDTO entityToDTO(Pet pet){
        return PetDTO.builder()
                .pno(pet.getPno())
                .petname(pet.getPetname())
                .regDate(pet.getRegdate())
                .updateDate(pet.getUpdatedate())
                .age(pet.getAge())
                .sex(pet.getSex())
                .member(pet.getMember())
                .isNeutralized(pet.getIsNeutralized())
                .species(pet.getSpecies())
                .type(pet.getType())
                .build();
    }


    default PetDTO entitiesToDTO(Pet pet, List<PetImage> petImages){
        PetDTO petDTO = PetDTO.builder()
                .pno(pet.getPno())
                .petname(pet.getPetname())
                .regDate(pet.getRegdate())
                .updateDate(pet.getUpdatedate())
                .age(pet.getAge())
                .sex(pet.getSex())
                .member(pet.getMember())
                .isNeutralized(pet.getIsNeutralized())
                .species(pet.getSpecies())
                .type(pet.getType())
                .build();

        List<PetImageDTO> petImageDTOList = petImages.stream().map(petImage -> {
            return PetImageDTO.builder().fileName(petImage.getFileName())
                    .pino(petImage.getPino())

                    .regDate(petImage.getRegDate())
                    .uploadPath(petImage.getUploadPath())
                    .uuid(petImage.getUuid())
                    .pino(petImage.getPino())
                    .updateDate(petImage.getUpdateDate())
                    .build();

        }).collect(Collectors.toList());

        petDTO.setPetImageDTOList(petImageDTOList);


        return petDTO;

    }

    default Map<String, Object> dtoToEntities(PetDTO petDTO){

        Map<String, Object> entityMap = new HashMap<>();

        Pet pet = Pet.builder()
                .pno(petDTO.getPno())
                .petname(petDTO.getPetname())
                .regdate(petDTO.getRegDate())
                .updatedate(petDTO.getUpdateDate())
                .age(petDTO.getAge())
                .sex(petDTO.getSex())
                .member(petDTO.getMember())
                .isNeutralized(petDTO.getIsNeutralized())
                .species(petDTO.getSpecies())
                .type(petDTO.getType())
                .build();

        entityMap.put("pet", pet);

        List<PetImageDTO> imageDTOList = petDTO.getPetImageDTOList();

        if(imageDTOList != null && imageDTOList.size() > 0 ) { //MovieImageDTO 처리

            List<PetImage> petImageList = imageDTOList.stream().map(petImageDTO ->{

                PetImage petImage = PetImage.builder().fileName(petImageDTO.getFileName())
                        .pino(petImageDTO.getPino())
                        .pet(pet)
                        .regDate(petImageDTO.getRegDate())
                        .uploadPath(petImageDTO.getUploadPath())
                        .uuid(petImageDTO.getUuid())
                        .pino(petImageDTO.getPino())
                        .updateDate(petImageDTO.getUpdateDate())
                        .build();

                return petImage;

            }).collect(Collectors.toList());

            // 첫 이미지를 메인 이미지 설정
            PetImage first = petImageList.get(0);
            if( null != first ){
                first.setMain();
            }

            entityMap.put("imageList", petImageList);
        }

        return entityMap;
    }


}
