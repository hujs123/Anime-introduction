package com.example.demo.controller;

import com.example.demo.config.enums.ReturnData;
import com.example.demo.entity.StaffInfoEntity;
import com.example.demo.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author hujs
 * @date 2024/07/03
 * @description
 */


@Slf4j
@RestController
@Tag(name = "人员操作-person", description = "人员信息相关操作")
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

    @Operation(summary = "添加人员信息", description = "添加人员信息")
    @PostMapping("addPersonInfo")
    public ReturnData addPersonInfo(@RequestBody String jsonString){
        return personService.addPersonInfo(jsonString);
    }

    @Operation(summary = "修改人员信息", description = "修改人员信息")
    @PostMapping("updatePersonInfo")
    public ReturnData updatePersonInfo(@RequestBody String jsonString){
        return personService.updatePersonInfo(jsonString);
    }

    @Operation(summary = "删除人员信息", description = "删除人员信息")
    @PostMapping("deletePersonInfo")
    public ReturnData deletePersonInfo(@RequestBody String jsonString){
        return personService.deletePersonInfo(jsonString);
    }

    @Operation(summary = "批量添加人员信息", description = "上传模板，批量添加人员信息")
    @PostMapping("uploadPersonsInfo")
    public ReturnData uploadPersonsInfo(@RequestPart("file") @Parameter(description = "要上传的Excel文件") MultipartFile file){
        if (file.isEmpty()) {
            return ReturnData.error("文件不能为空");
        }
        List<StaffInfoEntity> staffInfoEntityList = new ArrayList<>();
        //前端
//     <form method="post" action="/api/upload/excel" enctype="multipart/form-data">
//     <input type="file" name="file" required>
//        <button type="submit">上传</button>
//    </form>
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0); // 假设数据在第一张表上
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row currentRow = rowIterator.next();
                if (currentRow.getRowNum() == 0) continue; // 假设第一行是标题行
                StaffInfoEntity staffInfoEntity = new StaffInfoEntity();
                staffInfoEntity.setId(Integer.valueOf(String.valueOf(currentRow.getCell(0))));
                staffInfoEntity.setName(String.valueOf((currentRow.getCell(1))));
                staffInfoEntity.setGender(String.valueOf((currentRow.getCell(1))));
                staffInfoEntity.setEthnicity(String.valueOf((currentRow.getCell(1))));
                staffInfoEntity.setStar(String.valueOf((currentRow.getCell(1))));
                Date date = new Date();
                staffInfoEntity.setName(String.valueOf((currentRow.getCell(1))));
                staffInfoEntityList.add(staffInfoEntity);
            }

            // 这里可以根据需要处理staffList，比如保存到数据库

            return ReturnData.ok("Excel文件上传并解析成功" + staffInfoEntityList);
        } catch (IOException e) {
            return ReturnData.error("解析Excel文件时发生错误：" + e.getMessage());
        }
    }


}
