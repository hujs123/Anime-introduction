package com.example.demo.controller;

import com.example.demo.dto.StaffReqDto;
import com.example.demo.dto.StaffRspDto;
import com.example.demo.entity.StaffEntity;
import com.example.demo.service.StaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;


    @GetMapping("/hello")
    public String hello() {
        log.info("【调用接口】:api/users/hello");
        return "api/users/hello调用成功";
    }

    @PostMapping("getStaff")
    public List<StaffRspDto> getStaffs(@RequestBody StaffReqDto staffReqDto) {
        log.info("【请求地址】:api/users/getStaffs");
        log.info("【入参】：StaffReqDto - {}", staffReqDto);
        return staffService.getStaff(staffReqDto);
    }

    @PostMapping("getAllStaffs")
    public List<StaffEntity>getAllStaffs(){
        return staffService.getAllStaffs();
    }

    @PostMapping("getStaffByName")
    public StaffEntity getStaffByName(String name){
        return staffService.getStaffByName(name);
    }
}
