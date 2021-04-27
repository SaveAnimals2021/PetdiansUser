package org.petdians.user.animal.repository;

import org.petdians.user.animal.entity.ImageVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageVO, Long> {



}
