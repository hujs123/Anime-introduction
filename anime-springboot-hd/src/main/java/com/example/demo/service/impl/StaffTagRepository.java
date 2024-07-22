package com.example.demo.service.impl;


import com.example.demo.entity.StaffTagEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffTagRepository extends JpaRepository<StaffTagEntity, Long> {



    @Query("SELECT t FROM StaffTagEntity t WHERE t.id IN :tagList")
    List<StaffTagEntity> findByTagList(@Param("tagList") List<Integer> tagList);


//    @Query(value = "SELECT a.id FROM staff_info a INNER JOIN staff_tag_associations b ON a.id = b.staff_id " +
//            "WHERE b.tag_id IN :tagIds " +
//            "GROUP BY a.id " +
//            "HAVING COUNT(DISTINCT b.tag_id) = :tagCount", nativeQuery = true)
//    List<StaffTagEntity> findByTagList(@Param("tagList") List<Integer> tagList);

}
