package com.example.demo.service;

import com.example.demo.dto.StaffReqDto;
import com.example.demo.dto.StaffRspDto;
import com.example.demo.entity.StaffEntity;
import com.example.demo.mapper.StaffMapper;
import com.example.demo.service.impl.StaffRepository;

import com.example.demo.thirdInterface.thireInterface;
import com.example.demo.utils.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private EncryptionUtils encryptionUtils;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private thireInterface thireInterface;

    public List<StaffRspDto> getStaff(StaffReqDto staffReqDto){
        log.info("【查询入参】：staffReqDto.getName() - {}", staffReqDto.getName());
        log.info("【查询入参】：staffReqDto.getName()", staffReqDto.getName());
        log.info("【查询入参】：staffReqDto.getClass().toString() - {}", staffReqDto.getClass().toString());
        log.info("【查询入参】：staffReqDto - {}", staffReqDto);
        List<StaffEntity> staffEntityList = staffMapper.queryStaffs(staffReqDto.getName());
        log.info("【查询出参】：StaffReqDto - {}", staffEntityList);
        List<StaffRspDto> staffRspDtoList=new ArrayList<>();
        for (StaffEntity staffEntity:staffEntityList){
            StaffRspDto staffRspDto=new StaffRspDto();
            staffRspDto.setId(staffEntity.getId());
            staffRspDto.setName(staffEntity.getName());
            staffRspDto.setGender(staffEntity.getGender());
            staffRspDto.setAge(staffEntity.getAge());
            staffRspDto.setEthnicity(staffEntity.getEthnicity());
            staffRspDto.setPosition(staffEntity.getPosition());
            staffRspDto.setOrganize(staffEntity.getOrganize());
            staffRspDtoList.add(staffRspDto);
        }
        log.info("【出参】：StaffReqDto - {}", staffRspDtoList);
        return staffRspDtoList;
    };

    public List<StaffEntity> getAllStaffs(){
        log.info("出参：111");
        List<StaffEntity> staffEntityList = staffRepository.findAll();
        log.info("出参：222"+ staffEntityList);
        return staffEntityList;
    };

    public StaffEntity getStaffByName(String name){
//        log.info("【入参】：StaffReqDto - {}", name);
        StaffEntity staffEntity = staffRepository.findByName(name);
//        log.info("【入参】：StaffReqDto - {}", staffEntity);
        return staffEntity;
    };


    public String getPixiv(){
        thireInterface.thirdServerInterface(null,null,null);
        return "OK";
    };

}
