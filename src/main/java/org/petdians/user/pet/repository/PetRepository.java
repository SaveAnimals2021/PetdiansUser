package org.petdians.user.pet.repository;

import org.petdians.user.member.entity.Member;
import org.petdians.user.pet.entity.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("select p, pi from Pet p " +
            "left join PetImage pi on pi.pet = p " +
            "where pi.isMain = true " +
            "group by p")
    Page<Object[]> getPagedList(Pageable page);



    @Query(value = "select p, count(pi) from Pet p " +
            "inner join p.member m " +
            "left join PetImage  pi On pi.pet = p " +
            "where m.memberID = :memberid " +
            "group by p", countQuery = "select count(p) from Pet p")
    Object[] getPetsByMemberID(String memberid);


}
