package org.petdians.user.animal.repository;

import org.petdians.user.animal.entity.ImageVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ImageRepository extends JpaRepository<ImageVO, Integer> {

    @Query("select image from ImageVO image")
    public List<ImageVO> getAllImage();

    @Query("select image from ImageVO image " +
            "inner join image.missingAnimalVO animal " +
            "where animal.rescueStatus = 0")
    public List<ImageVO> getRescueImage();

}
