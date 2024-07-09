package com.example.demo.service.impl;

import com.example.demo.entity.DictEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DictRepository extends JpaRepository<DictEntity, Long> {

}
