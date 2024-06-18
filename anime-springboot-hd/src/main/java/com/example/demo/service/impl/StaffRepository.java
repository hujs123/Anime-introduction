package com.example.demo.service.impl;

import com.example.demo.entity.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Long> {

    StaffEntity findByName(String name);
}
