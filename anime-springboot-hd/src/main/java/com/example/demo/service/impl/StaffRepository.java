package com.example.demo.service.impl;

import com.example.demo.entity.StaffInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<StaffInfoEntity, Long> {

    StaffInfoEntity findByName(String name);

    StaffInfoEntity findById(Integer Id);

    // 使用JPQL查询名字包含"博士"的所有员工
    @Query("SELECT s FROM StaffInfoEntity s WHERE s.name LIKE %?1%")
    List<StaffInfoEntity> findByNameContaining(String name);
}
