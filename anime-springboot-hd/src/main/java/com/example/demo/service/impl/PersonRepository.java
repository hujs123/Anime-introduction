package com.example.demo.service.impl;

import com.example.demo.entity.PersonInfoEntity;
import com.example.demo.entity.StaffInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PersonRepository extends JpaRepository<PersonInfoEntity, Long> {
    PersonInfoEntity findByName(String name);
}
