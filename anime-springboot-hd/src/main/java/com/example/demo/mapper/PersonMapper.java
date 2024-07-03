package com.example.demo.mapper;


import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.example.demo.entity.PersonInfoEntity;
import com.example.demo.entity.StaffInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface PersonMapper {

    List<PersonInfoEntity> getPagePerson(String name, String gender, String ethnicity, String position, String tags,  String isStaff);
}
