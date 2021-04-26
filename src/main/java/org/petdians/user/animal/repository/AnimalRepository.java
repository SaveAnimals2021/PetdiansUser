package org.petdians.user.animal.repository;

import org.petdians.user.animal.entity.MissingAnimalInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnimalRepository extends JpaRepository<MissingAnimalInfo, Integer> {

//    @Query(value = "select animal, member, count(r) from tbl_animal animal " +
//            "left join tbl_member member " +
//            "left outer join Reply reply on reply.animal = animal " +
//            "group by animal"
//            ,
//            countQuery = "select count (animal) from tbl_animmal animal");
//    Page<Object[]> getAnimalWithReplyCount(Pageable pageable);

}
