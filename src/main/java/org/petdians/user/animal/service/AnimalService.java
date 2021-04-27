package org.petdians.user.animal.service;

import org.petdians.user.animal.dto.ImageDTO;
import org.petdians.user.animal.dto.MissingAnimalDTO;
import org.petdians.user.animal.entity.ImageVO;
import org.petdians.user.animal.entity.MissingAnimalVO;
import org.petdians.user.common.dto.PageRequestDTO;
import org.petdians.user.common.dto.PageResultDTO;
import org.petdians.user.common.util.SimpleDateFormatter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public interface AnimalService {

    PageResultDTO<MissingAnimalDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    public Page<Object[]> getAnimalList(Pageable pageable);

    default MissingAnimalVO dtoToEntity(MissingAnimalDTO dto) throws Exception{

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");

        Date missingDate = format.parse(dto.getMissingDate());
        Date rescueDate = format.parse(dto.getRescueDate());
        Date regDate = format.parse(dto.getRegDate());
        Date updateDate = format.parse(dto.getUpdateDate());

        return MissingAnimalVO.builder()
                .animalNumber(dto.getAnimalNumber()).animalCode(dto.getAnimalCode()).type(dto.getType()).serviceName(dto.getServiceName())
                .name(dto.getName()).species(dto.getSpecies()).sex(dto.getSex()).age(dto.getAge()).situation(dto.getSituation())
                .special(dto.getSpecial()).color(dto.getColor()).missingDate(missingDate).regDate(regDate).updateDate(updateDate)
                .originUrl(dto.getOriginURL()).missingLocation(dto.getMissingLocation()).rescueLocation(dto.getRescueLocation()).rescueDate(rescueDate)
                .rescueStatus(dto.getRescueStatus()).bno(dto.getBno()).guardianName(dto.getGuardianName()).phoneNumber(dto.getPhoneNumber()).isCompleted(dto.getIsCompleted())
                .build();
    }

    default MissingAnimalDTO entitysToDTO(MissingAnimalVO missingAnimalVO, List<ImageVO> images) {

        SimpleDateFormatter formatter = new SimpleDateFormatter();

        MissingAnimalDTO animalDTO = new MissingAnimalDTO();

        //MissingAnimalVO -> MissingAnimalDTO
        animalDTO.setAnimalNumber(missingAnimalVO.getAnimalNumber());
        animalDTO.setAnimalCode(missingAnimalVO.getAnimalCode());
        animalDTO.setType(missingAnimalVO.getType());
        animalDTO.setServiceName(missingAnimalVO.getServiceName());
        animalDTO.setName(missingAnimalVO.getName());
        animalDTO.setSpecies(missingAnimalVO.getSpecies());
        animalDTO.setSex(missingAnimalVO.getSex());
        animalDTO.setAge(missingAnimalVO.getAge());
        animalDTO.setSpecial(missingAnimalVO.getSpecial());
        animalDTO.setColor(missingAnimalVO.getColor());

        animalDTO.setMissingDate(formatter.dateToString(missingAnimalVO.getMissingDate()));
        animalDTO.setMissingLocation(missingAnimalVO.getMissingLocation());
        animalDTO.setRegDate(formatter.dateToString(missingAnimalVO.getRegDate()));
        animalDTO.setUpdateDate(formatter.dateToString(missingAnimalVO.getUpdateDate()));
        animalDTO.setOriginURL(missingAnimalVO.getOriginUrl());
        animalDTO.setIsCompleted(missingAnimalVO.getIsCompleted());



        animalDTO.setSituation(missingAnimalVO.getSituation());
        animalDTO.setRescueStatus(missingAnimalVO.getRescueStatus());
        animalDTO.setBno(missingAnimalVO.getBno());
        animalDTO.setGuardianName(missingAnimalVO.getGuardianName());
        animalDTO.setPhoneNumber(missingAnimalVO.getPhoneNumber());

        animalDTO.setRescueDate(formatter.dateToString(missingAnimalVO.getRescueDate()));
        animalDTO.setRescueLocation(missingAnimalVO.getRescueLocation());
        animalDTO.setIsCompleted(missingAnimalVO.getIsCompleted());

        //ImageVO -> ImageDTO
        List<ImageDTO> imageDTOList = images.stream().map(imageVO -> {

            System.out.println(imageVO.getUploadPath());

            String uploadPath = "";

            if (null != imageVO.getUploadPath()) {

            uploadPath = imageVO.getUploadPath().substring(10);

            } else {

                uploadPath = null;
            }


            return ImageDTO.builder()
                    .animalNumber(imageVO.getMissingAnimalVO().getAnimalNumber())
                    .fileName(imageVO.getFileName())
                    .uuid(imageVO.getUuid())
                    .type(imageVO.getType())
                    .uploadPath(uploadPath)
                    .build();

        }).collect(Collectors.toList());

        animalDTO.setImageDTOList(imageDTOList);

        return animalDTO;

    }

}
