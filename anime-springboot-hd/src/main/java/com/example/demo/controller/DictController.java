package com.example.demo.controller;

import com.example.demo.config.enums.ReturnData;
import com.example.demo.service.DictService;
import com.example.demo.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;


/**
 * @author hujs
 * @date 2024/07/03
 * @description
 */
@Slf4j
@RestController
@Tag(name = "字典操作-dict", description = "字典相关操作")
@RequestMapping("/api/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @Operation(summary = "查询字典", description = "查询字典")
    @PostMapping("/getDict")
    public ReturnData getDict(@RequestBody String jsonString) {
        log.info("【调用接口】:api/dict/getDict");
        return dictService.getDict(jsonString);
    }

    @Operation(summary = "分页查询字典", description = "分页查询字典")
    @PostMapping("/getPageDict")
    public ReturnData getPageDict(@RequestBody String jsonString) {
        log.info("【调用接口】:api/dict/getPageDict");
        return dictService.getPageDict(jsonString);
    }


    @Operation(summary = "增加字典字段", description = "增加字典字段")
    @PostMapping("/addDict")
    public ReturnData addDict(@RequestBody String jsonString) {
        log.info("【调用接口】:api/dict/addDict");
        return dictService.addDict(jsonString);
    }

    @Operation(summary = "修改字典字段", description = "修改字典字段")
    @PostMapping("/updateDict")
    public ReturnData updateDict(@RequestBody String jsonString) {
        log.info("【调用接口】:api/dict/updateDict");
        return dictService.updateDict(jsonString);
    }

    @Operation(summary = "删除字典字段", description = "删除字典字段")
    @PostMapping("/deleteDict")
    public ReturnData deleteDict(@RequestBody String jsonString) {
        log.info("【调用接口】:api/dict/deleteDict");
        return dictService.deleteDict(jsonString);
    }
}
