package com.example.demo.service;

import com.example.demo.config.enums.ReturnData;
import com.example.demo.dto.StaffReqDto;
import com.example.demo.entity.StaffInfoEntity;
import com.example.demo.mapper.StaffMapper;
import com.example.demo.service.impl.StaffRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffMapper staffMapper;

    public ReturnData getStaff(StaffReqDto staffReqDto) {
        log.info("【getStaff】：查询入参： - {}", staffReqDto);
        List<StaffInfoEntity> staffInfoEntityList = staffMapper.queryStaffs(staffReqDto.getName());
        log.info("【getStaff】：出参 - {}", staffInfoEntityList);
        return ReturnData.ok(staffInfoEntityList);
    }

    public ReturnData getAllStaffs() {
        List<StaffInfoEntity> staffInfoEntityList = staffRepository.findAll();
        log.info("【getAllStaffs】：出参 - {}", staffInfoEntityList);
        return ReturnData.ok(staffInfoEntityList);
    }

    public ReturnData getStaffByName(String name) {
        List<StaffInfoEntity> staffInfoEntityList = staffRepository.findByNameContaining(name);
        log.info("【getStaffByName】：出参 - {}", staffInfoEntityList);
        return ReturnData.ok(staffInfoEntityList);
    }

    public ReturnData addStaff(HttpServletRequest request) {
        String name = String.valueOf(request.getParameter("name"));
        String gender = String.valueOf(request.getParameter("gender"));
        String ethnicity = String.valueOf(request.getParameter("ethnicity"));
        String position = String.valueOf(request.getParameter("position"));
        String tags = String.valueOf(request.getParameter("tags"));
        String star = String.valueOf(request.getParameter("star"));

        StaffInfoEntity staffInfoEntity=new StaffInfoEntity();
        if (StringUtils.isEmpty(name)) {
            return ReturnData.error("name不可为空");
        }
        staffInfoEntity.setName(name);
        if (!StringUtils.isEmpty(gender)) {
            staffInfoEntity.setGender(gender);
        }
        if (!StringUtils.isEmpty(ethnicity)) {
            staffInfoEntity.setEthnicity(ethnicity);
        }
        if (!StringUtils.isEmpty(star)) {
            staffInfoEntity.setStar(star);
        }
        log.info("【addStaff】：存储数据： - {}", staffInfoEntity);
        staffRepository.save(staffInfoEntity); // 批量保存实体到数据库
        return ReturnData.ok("添加干员成功");
    }

};




