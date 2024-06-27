package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.entity.InvoiceDataEntity;
import com.example.demo.service.SkillsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @author hujs
 * @date 2024/06/24
 * @description
 */
@Slf4j
@RestController
@Tag(name = "功能Controller", description = "各个功能实现实例")
@RequestMapping("/api/skills")
public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    @Operation(summary = "调用接口", description = "调用第三方接口")
    @PostMapping("/callThirdInterface")
    public ReturnData callThirdInterface() {
        return skillsService.callThirdInterface();
    }

    @Operation(summary = "base64转Image", description = "调用第三方接口")
    @GetMapping("/base64ToImage")
    public ReturnData base64ToImage() {
        return skillsService.base64ToImage();
    }

    @Operation(summary = "表单入参查询", description = "表单入参查询")
    @GetMapping("/httpServletRequest")
    public ReturnData httpServletRequest(HttpServletRequest request){
        return skillsService.httpServletRequest(request);
    }

    @Operation(summary = "分页查询", description = "分页查询省份下的城市")
    @PostMapping("/getPageCity")
    public ReturnData getPageCity(@RequestParam String pageNum,@RequestParam String pageSize){
        log.info("【getPageCity】进入方法");
        return skillsService.getPageCity(pageNum,pageSize);
    }



}
