package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.service.SkillsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hujs
 * @date 2024/06/24
 * @description
 */
@Slf4j
@RestController
@Tag(name = "功能Controller", description = "各个功能实现实例")
@RequestMapping("/api/interface")
public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    @Operation(summary = "调用接口", description = "调用第三方接口")
    @PostMapping("/callThirdInterface")
    public ReturnData callThirdInterface(@RequestBody Object object) {
        log.info("【调用接口】:jsonObject"+object);
        return skillsService.callThirdInterface(object);
    }

}
