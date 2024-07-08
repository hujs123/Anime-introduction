package com.example.demo.service.impl;

import com.example.demo.entity.StaffTagAssociationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hujs
 * @date 2024/07/05
 * @description
 */
@Repository
public interface StaffTagAssociationsRepository extends JpaRepository<StaffTagAssociationsEntity, Long> {
    @Query(value = "SELECT a.* FROM staff_info a INNER JOIN staff_tag_associations b ON a.id = b.staff_id " +
            "WHERE b.tag_id IN :tagIds " +
            "GROUP BY a.id " +
            "HAVING COUNT(DISTINCT b.tag_id) = :tagCount", nativeQuery = true)
    List<Integer> findStaffInfoByAllTagsNative(@Param("tagIds") List<Integer> tagIds, @Param("tagCount") int tagCount);


}
