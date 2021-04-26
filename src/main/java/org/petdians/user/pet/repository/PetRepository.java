package org.petdians.user.pet.repository;

import org.petdians.user.member.entity.Member;
import org.petdians.user.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {



}
