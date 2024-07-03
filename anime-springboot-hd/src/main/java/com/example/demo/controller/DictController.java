package com.example.demo.controller;

import com.example.demo.config.enums.ReturnData;
import com.example.demo.service.DictService;
import com.example.demo.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hujs
 * @date 2024/07/03
 * @description
 */
@Slf4j
@RestController
@Tag(name = "字典操作-dict", description = "字典相关信息查询接口的说明")
@RequestMapping("/api/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @Operation(summary = "查询干员职阶", description = "查询干员职阶")
    @PostMapping("/getDictPostion")
    public ReturnData getDictPostion(@RequestParam String dictType) {
        log.info("【调用接口】:api/person/getPagePerson");
        return dictService.getDictPostion(dictType);
    }
}
