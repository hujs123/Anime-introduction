package com.example.demo.mapper;


import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.example.demo.entity.StaffEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@InterceptorIgnore(tenantLine = "true")
public interface StaffMapper {

    List<StaffEntity> queryStaffs(@Param("name") String name);

    List<StaffEntity> queryAllStaffs();
}
