package com.example.demo.mapper;


import com.example.demo.entity.StaffEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StaffMapper {

    List<StaffEntity> queryStaffs(@Param("name") String name);
}
