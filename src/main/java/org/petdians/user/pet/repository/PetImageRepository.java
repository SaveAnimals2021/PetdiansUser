package org.petdians.user.pet.repository;

import org.petdians.user.pet.entity.Pet;
import org.petdians.user.pet.entity.PetImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PetImageRepository extends JpaRepository<PetImage, Long> {

    @Query(value = "select pi from PetImage pi " +
            "left join pi.pet p " +
            "where p.pno = :pno")
    Object[] getPetImagesByPno(Long pno);

}
