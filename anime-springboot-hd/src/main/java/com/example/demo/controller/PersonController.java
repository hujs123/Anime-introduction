package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author hujs
 * @date 2024/07/03
 * @description
 */


@Slf4j
@RestController
@Tag(name = "人员操作-person", description = "人员相关信息查询接口的说明")
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Operation(summary = "分页查询人员信息", description = "分页查询人员信息")
    @PostMapping("/getPagePerson")
    public ReturnData getPagePerson(@RequestBody String jsonString) {
        log.info("【调用接口】:api/person/getPagePerson");
        return personService.getPagePerson(jsonString);
    }
}
