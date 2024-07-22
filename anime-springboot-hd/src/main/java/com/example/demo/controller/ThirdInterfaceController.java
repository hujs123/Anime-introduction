package com.example.demo.controller;

import com.example.demo.config.enums.ReturnData;
import com.example.demo.service.ThirdInterfaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = "三方接口-thirdInterface", description = "第三方接口")
@RequestMapping("/api/thirdInterface")
public class ThirdInterfaceController {

    @Operation(summary = "hello罗德岛测试", description = "hello罗德岛测试的API")
    @GetMapping("/hello")
    public String hello() {
        log.info("【调用接口】:api/users/hello");
        return "api/users/hello调用成功";
    }

    @Autowired
    private ThirdInterfaceService thirdInterfaceService;

    @Operation(summary = "获取图片", description = "获取随机图片")
    @PostMapping("getPixivs")
    public ReturnData getPixivs(String name, Integer num, String keyword){
        return thirdInterfaceService.getPixivs(name,num,keyword);
    }

    @Operation(summary = "获取中国省份", description = "获取中国省份")
    @PostMapping("getProvince")
    public ReturnData getProvince(){
        return thirdInterfaceService.getProvince();
    }
}
