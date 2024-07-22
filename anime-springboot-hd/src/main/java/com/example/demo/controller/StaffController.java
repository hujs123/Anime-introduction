package com.example.demo.controller;

import com.example.demo.config.enums.ReturnData;
import com.example.demo.dto.StaffReqDto;
import com.example.demo.service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@Tag(name = "干员操作-staff", description = "干员相关信息查询接口的说明")
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;


    @Operation(summary = "分页查询干员", description = "条件分页查询干员")
    @PostMapping("getPageStaff")
    public ReturnData getPageStaff(@RequestBody String jsonString) {
        return staffService.getPageStaff(jsonString);
    }


    @Operation(summary = "修改干员信息", description = "修改干员信息")
    @PostMapping("updateStaff")
    public ReturnData updateStaff(@RequestBody String jsonString){
        return staffService.updateStaff(jsonString);
    }


    @Operation(summary = "查询干员", description = "条件查询干员的API")
    @PostMapping("getStaff")
    public ReturnData getStaffs(@RequestBody StaffReqDto staffReqDto) {
        return staffService.getStaff(staffReqDto);
    }
    @Operation(summary = "查询所有干员", description = "查询所有干员的API")
    @PostMapping("getAllStaffs")
    public ReturnData getAllStaffs(){
        return staffService.getAllStaffs();
    }

    @Operation(summary = "查询干员", description = "通过名字查询干员的API")
    @PostMapping("getStaffByName")
    public ReturnData getStaffByName(String name){
        log.info("【getStaffByName】：name - {}", name);
        return staffService.getStaffByName(name);
    }

    @Operation(summary = "添加干员", description = "添加干员")
    @PostMapping("addStaff")
    public ReturnData addStaff(HttpServletRequest request){
        return staffService.addStaff(request);
    }






}
