package org.petdians.user.animal.service;

import org.petdians.user.animal.dto.MissingAnimalDTO;
import org.petdians.user.animal.entity.MissingAnimalVO;
import org.petdians.user.common.dto.PageRequestDTO;
import org.petdians.user.common.dto.PageResultDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface AnimalService {

    PageResultDTO<MissingAnimalDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    public Page<Object> getAnimalList(Pageable pageable);

    default MissingAnimalVO dtoToEntity(MissingAnimalDTO dto) throws Exception{
        Date missingDate = dto.getMissingDate();
        Date rescueDate = dto.getRescueDate();
        Date regDate = dto.getRegDate();
        Date updateDate = dto.getUpdateDate();



        return MissingAnimalVO.builder()
                .animalNumber(dto.getAnimalnumber()).animalCode(dto.getAnimalCode()).type(dto.getType()).serviceName(dto.getServiceName())
                .name(dto.getName()).species(dto.getSpecies()).sex(dto.getSex()).age(dto.getAge()).situation(dto.getSituation())
                .special(dto.getSpecial()).color(dto.getColor()).missingDate(missingDate).regDate(regDate).updateDate(updateDate)
                .originUrl(dto.getOriginURL()).missingLocation(dto.getMissingLocation()).rescueLocation(dto.getRescueLocation()).rescueDate(rescueDate)
                .rescueStatus(dto.getRescueStatus()).bno(dto.getBno()).guardianName(dto.getGuardianName()).phoneNumber(dto.getPhoneNumber()).isCompleted(dto.getIsCompleted())
                .build();
    }

    default MissingAnimalDTO entityToDTO(MissingAnimalVO vo) {
        MissingAnimalDTO animalDTO = new MissingAnimalDTO();

        animalDTO.setAnimalnumber(vo.getAnimalNumber());
        animalDTO.setAnimalCode(vo.getAnimalCode());
        animalDTO.setType(vo.getType());
        animalDTO.setServiceName(vo.getServiceName());
        animalDTO.setName(vo.getName());
        animalDTO.setSpecies(vo.getSpecies());
        animalDTO.setSex(vo.getSex());
        animalDTO.setAge(vo.getAge());
        animalDTO.setSpecial(vo.getSpecial());
        animalDTO.setColor(vo.getColor());

        animalDTO.setMissingDate(vo.getMissingDate());
        animalDTO.setMissingLocation(vo.getMissingLocation());
        animalDTO.setRegDate(vo.getRegDate());
        animalDTO.setUpdateDate(vo.getUpdateDate());
        animalDTO.setOriginURL(vo.getOriginUrl());
        animalDTO.setIsCompleted(vo.getIsCompleted());



        animalDTO.setSituation(vo.getSituation());
        animalDTO.setRescueStatus(vo.getRescueStatus());
        animalDTO.setBno(vo.getBno());
        animalDTO.setGuardianName(vo.getGuardianName());
        animalDTO.setPhoneNumber(vo.getPhoneNumber());

        animalDTO.setRescueDate(vo.getRescueDate());
        animalDTO.setRescueLocation(vo.getRescueLocation());
        animalDTO.setIsCompleted(vo.getIsCompleted());

        return animalDTO;
    }

}
