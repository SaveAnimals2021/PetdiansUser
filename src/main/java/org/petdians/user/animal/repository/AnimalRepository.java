package org.petdians.user.animal.repository;

import org.petdians.user.animal.entity.MissingAnimalVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalRepository extends JpaRepository<MissingAnimalVO, Integer> {

    @Query("select animal, min(image) from MissingAnimalVO animal " +
            "left join ImageVO image on image.missingAnimalVO = animal " +
            "where animal.rescueStatus = 0 group by animal")
    Page<Object[]> getAnimalListWithImage(Pageable pageable);

    @Query("select animal, image from MissingAnimalVO animal " +
            "left join ImageVO image on image.missingAnimalVO = animal " +
            "where animal.animalNumber = :animalNumber  group by image")
    List<Object[]> getAnimalWithImageList(@Param("animalNumber") Integer animalNumber);





}
