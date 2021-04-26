package org.petdians.user.animal.repository;

import org.petdians.user.animal.entity.MissingAnimalInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnimalRepository extends JpaRepository<MissingAnimalInfo, Integer> {

    @Query(value = "select animal from MissingAnimalInfo animal")
    Page<Object> getAnimalWithReplyCount(Pageable pageable);

}
