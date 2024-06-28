package com.example.demo.mapper;


import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.example.demo.entity.StaffInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface StaffMapper {

    List<StaffInfoEntity> queryStaffs(@Param("name") String name);

    List<StaffInfoEntity> queryAllStaffs();
}
