package com.example.demo.service.impl;

import com.example.demo.entity.StaffTagAssociationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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

    // 使用@Modifying和@Query注解定义自定义删除操作
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM StaffTagAssociationsEntity sta WHERE sta.staffId  = :staffId")
    void deleteAllByStaffId(Integer staffId);

}
