package org.petdians.user.animal.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.petdians.user.animal.dto.MissingAnimalDTO;
import org.petdians.user.animal.entity.ImageVO;
import org.petdians.user.animal.entity.MissingAnimalVO;
import org.petdians.user.animal.repository.AnimalRepository;
import org.petdians.user.animal.repository.ImageRepository;
import org.petdians.user.common.dto.PageRequestDTO;
import org.petdians.user.common.dto.PageResultDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class AnimalServiceImpl implements AnimalService{

    private final AnimalRepository animalRepository;

    private final ImageRepository imageRepository;

    @Override
    public PageResultDTO<MissingAnimalDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {

        pageRequestDTO.setSize(6);

        Pageable pageable = pageRequestDTO.getPageable(Sort.by("animalNumber").descending());

        Page<Object[]> result = animalRepository.getAnimalListWithImage(pageable);

        Function<Object[], MissingAnimalDTO> fn = ( arr -> entitysToDTO( (MissingAnimalVO)arr[0], (List<ImageVO>)(Arrays.asList((ImageVO)arr[1])) ) );

        return new PageResultDTO(result, fn);
    }

    @Override
    public Page<Object[]> getAnimalList(Pageable pageable) {
        return animalRepository.getAnimalListWithImage(pageable);
    }

    @Override
    @Transactional
    public Integer register(MissingAnimalDTO missingAnimalDTO) {

        Map<String, Object> entityMap = dtoToEntity(missingAnimalDTO);
        MissingAnimalVO missingAnimalVO = (MissingAnimalVO) entityMap.get("missingAnimalVO");
        List<ImageVO> imageVOList = (List<ImageVO>) entityMap.get("imageVOList");

        log.info(missingAnimalVO);
        log.info(imageVOList);

        //동물정보 등록
        MissingAnimalVO result = animalRepository.save(missingAnimalVO);

        //동물 이미지 정보 등록 - 등록한 동물 번호 이미지에 삽입
        imageVOList.forEach(imageVO -> {

            imageVO.changeMissingAnimalVO(result.getAnimalNumber());
            imageRepository.save(imageVO);

        });

        return missingAnimalVO.getAnimalNumber();

    }

    @Override
    public MissingAnimalDTO getAnimal(Integer animalNumber) {

        List<Object[]> result = animalRepository.getAnimalWithImageList(animalNumber);

        MissingAnimalVO missingAnimalVO = (MissingAnimalVO) result.get(0)[0];

        List<ImageVO> imageVOList = new ArrayList<>();

        result.forEach(arr -> {
            ImageVO imageVO = (ImageVO) arr[1];
            imageVOList.add(imageVO);
        });

        return entitysToDTO(missingAnimalVO, imageVOList);
    }

}
