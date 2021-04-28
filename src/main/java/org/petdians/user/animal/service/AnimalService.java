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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface AnimalService {

    PageResultDTO<MissingAnimalDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    public Page<Object[]> getAnimalList(Pageable pageable);

    public Integer register(MissingAnimalDTO missingAnimalDTO);





    default Map<String, Object> dtoToEntity(MissingAnimalDTO dto){

        Map<String, Object> entityMap = new HashMap<>();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");

        Date missingDate = null;
        Date rescueDate = null;
        Date regDate = null;
        Date updateDate = null;

        try {

           missingDate = format.parse(dto.getMissingDate());
            rescueDate = format.parse(dto.getRescueDate());
            regDate = format.parse(dto.getRegDate());
            updateDate = format.parse(dto.getUpdateDate());

        } catch (Exception e) {
            e.printStackTrace();
        }

        MissingAnimalVO missingAnimalVO = MissingAnimalVO.builder()
                .animalNumber(dto.getAnimalNumber()).animalCode(dto.getAnimalCode()).type(dto.getType()).serviceName(dto.getServiceName())
                .name(dto.getName()).species(dto.getSpecies()).sex(dto.getSex()).age(dto.getAge()).situation(dto.getSituation())
                .special(dto.getSpecial()).color(dto.getColor()).missingDate(missingDate).regDate(regDate).updateDate(updateDate)
                .originUrl(dto.getOriginURL()).missingLocation(dto.getMissingLocation()).rescueLocation(dto.getRescueLocation()).rescueDate(rescueDate)
                .rescueStatus(dto.getRescueStatus()).bno(dto.getBno()).guardianName(dto.getGuardianName()).phoneNumber(dto.getPhoneNumber()).isCompleted(dto.getIsCompleted())
                .build();

        entityMap.put("missingAnimalVO", missingAnimalVO);

        List<ImageDTO> imageDTOList = dto.getImageDTOList();

        if (imageDTOList != null && imageDTOList.size() > 0) { //MovieImageDTO 처리

            List<ImageVO> imageVOList = imageDTOList.stream().map(imageDTO -> {

//                private Integer ino;
//                private String url;
//                private MissingAnimalVO missingAnimalVO;
//                private String uuid;
//                private String uploadPath;
//                private String fileName;
//                private String type;
//
//                private Date regDate;
//                private Date updateDate;

                ImageVO imageVO = ImageVO.builder()
                        .missingAnimalVO(MissingAnimalVO.builder().animalNumber(imageDTO.getAnimalNumber()).build())
                        .uuid(imageDTO.getUuid())
                        .fileName(imageDTO.getFileName())
                        .uploadPath(imageDTO.getUploadPath())
                        .type(imageDTO.getType())
                        .build();
                return imageVO;

            }).collect(Collectors.toList());
            //end List<ImageVO>
            entityMap.put("imageVOList", imageVOList);
        }

        return entityMap;
    }

    default MissingAnimalDTO entitysToDTO(MissingAnimalVO missingAnimalVO, List<ImageVO> images) {

        SimpleDateFormatter formatter = new SimpleDateFormatter();

        MissingAnimalDTO missingAnimalDTO = new MissingAnimalDTO();

        //MissingAnimalVO -> MissingAnimalDTO
        missingAnimalDTO.setAnimalNumber(missingAnimalVO.getAnimalNumber());
        missingAnimalDTO.setAnimalCode(missingAnimalVO.getAnimalCode());
        missingAnimalDTO.setType(missingAnimalVO.getType());
        missingAnimalDTO.setServiceName(missingAnimalVO.getServiceName());
        missingAnimalDTO.setName(missingAnimalVO.getName());
        missingAnimalDTO.setSpecies(missingAnimalVO.getSpecies());
        missingAnimalDTO.setSex(missingAnimalVO.getSex());
        missingAnimalDTO.setAge(missingAnimalVO.getAge());
        missingAnimalDTO.setSpecial(missingAnimalVO.getSpecial());
        missingAnimalDTO.setColor(missingAnimalVO.getColor());

        missingAnimalDTO.setMissingDate(formatter.dateToString(missingAnimalVO.getMissingDate()));
        missingAnimalDTO.setMissingLocation(missingAnimalVO.getMissingLocation());
        missingAnimalDTO.setRegDate(formatter.dateToString(missingAnimalVO.getRegDate()));
        missingAnimalDTO.setUpdateDate(formatter.dateToString(missingAnimalVO.getUpdateDate()));
        missingAnimalDTO.setOriginURL(missingAnimalVO.getOriginUrl());
        missingAnimalDTO.setIsCompleted(missingAnimalVO.getIsCompleted());



        missingAnimalDTO.setSituation(missingAnimalVO.getSituation());
        missingAnimalDTO.setRescueStatus(missingAnimalVO.getRescueStatus());
        missingAnimalDTO.setBno(missingAnimalVO.getBno());
        missingAnimalDTO.setGuardianName(missingAnimalVO.getGuardianName());
        missingAnimalDTO.setPhoneNumber(missingAnimalVO.getPhoneNumber());

        missingAnimalDTO.setRescueDate(formatter.dateToString(missingAnimalVO.getRescueDate()));
        missingAnimalDTO.setRescueLocation(missingAnimalVO.getRescueLocation());
        missingAnimalDTO.setIsCompleted(missingAnimalVO.getIsCompleted());

        //ImageVO -> ImageDTO
        List<ImageDTO> imageDTOList = images.stream().map(imageVO -> {

            String uploadPath = "";

            if (null != imageVO.getUploadPath() && 9 < imageVO.getUploadPath().length()) {

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

        missingAnimalDTO.setImageDTOList(imageDTOList);

        return missingAnimalDTO;

    }

}
