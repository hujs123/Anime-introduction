package com.example.demo.service.impl;

import com.example.demo.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Long> {

    StaffEntity findByName(String name);

    StaffEntity findById(Integer Id);

    // 使用JPQL查询名字包含"博士"的所有员工
    @Query("SELECT s FROM StaffEntity s WHERE s.name LIKE %?1%")
    List<StaffEntity> findByNameContaining(String name);
}
