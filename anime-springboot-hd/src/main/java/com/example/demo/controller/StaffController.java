package com.example.demo.controller;

import com.example.demo.dto.StaffReqDto;
import com.example.demo.dto.StaffRspDto;
import com.example.demo.entity.InptuEntity;
import com.example.demo.entity.InvoiceDataEntity;
import com.example.demo.entity.StaffEntity;
import com.example.demo.service.StaffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "干员接口", description = "干员相关信息查询接口的说明")
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;


    @Operation(summary = "hello罗德岛测试", description = "hello罗德岛测试的API")
    @GetMapping("/hello")
    public String hello() {
        log.info("【调用接口】:api/users/hello");
        return "api/users/hello调用成功";
    }

    @Operation(summary = "查询干员", description = "查询干员的API")
    @PostMapping("getStaff")
    public List<StaffRspDto> getStaffs(@RequestBody StaffReqDto staffReqDto) {
        log.info("【请求地址】:api/users/getStaffs");
        log.info("【入参】：StaffReqDto - {}", staffReqDto);
        return staffService.getStaff(staffReqDto);
    }

    @Operation(summary = "查询干员", description = "查询所有干员的API")
    @PostMapping("getAllStaffs")
    public List<StaffEntity>getAllStaffs(){
        return staffService.getAllStaffs();
    }

    @Operation(summary = "查询干员", description = "查询干员的API")
    @PostMapping("getStaffByName")
    public StaffEntity getStaffByName(String name){
        return staffService.getStaffByName(name);
    }


    @Operation(summary = "电子发票", description = "电子发票")
    @PostMapping("getdzfp")
    public String getdzfp() throws JAXBException, XMLStreamException {
        return staffService.getdzfp();
    }

    @Operation(summary = "测试", description = "测试")
    @PostMapping("test")
    public String test(@RequestBody InvoiceDataEntity invoiceDataEntity)  {
        return staffService.test(invoiceDataEntity);
    }


    @Operation(summary = "测试2", description = "测试2")
    @GetMapping("test2")
    public void test2(@RequestParam String value, HttpServletResponse response)  {
        //https://health.neuqsoft.com/zzjpay/H13010400915/aio/invoice?serialno=2024060021&visittype=01&amount=0.01&tenantId=H13010400915&paytype=
         staffService.test2(value,  response);
    }
}
