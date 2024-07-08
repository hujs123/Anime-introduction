package com.example.demo.controller;

import com.example.demo.config.enums.ReturnData;
import com.example.demo.entity.StaffInfoEntity;
import com.example.demo.service.ExcelService;
import com.example.demo.service.SkillsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author hujs
 * @date 2024/06/24
 * @description
 */
@Slf4j
@RestController
@Tag(name = "功能实现-skills", description = "各个功能实现实例")
@RequestMapping("/api/skills")
public class SkillsController {

    @Autowired
    private SkillsService skillsService;

    @Autowired
    private ExcelService excelService;

    @Operation(summary = "调用接口", description = "调用第三方接口")
    @PostMapping("/callThirdInterface")
    public ReturnData callThirdInterface() {
        return skillsService.callThirdInterface();
    }

    @Operation(summary = "base64转Image", description = "调用第三方接口")
    @GetMapping("/base64ToImage")
    public void base64ToImage(HttpServletResponse response) {
        skillsService.base64ToImage(response);
    }

    @Operation(summary = "表单入参查询", description = "表单入参查询")
    @GetMapping("/httpServletRequest")
    public ReturnData httpServletRequest(HttpServletRequest request) {
        return skillsService.httpServletRequest(request);
    }

    @Operation(summary = "分页查询", description = "分页查询省份下的城市")
    @PostMapping("/getPageCity")
    public ReturnData getPageCity(@RequestParam String pageNum, @RequestParam String pageSize) {
        log.info("【getPageCity】进入方法");
        return skillsService.getPageCity(pageNum, pageSize);
    }

    @Operation(summary = "获取文档（excel）", description = "获取excel文档")
    @GetMapping("/getDocument")
    public ResponseEntity<ByteArrayResource> getDocument() throws IOException {
        log.info("【getDocument】进入方法");
        return skillsService.getDocument();
    }

    @Operation(summary = "上传干员信息（excel）", description = "模板插入干员信息")
    @PostMapping(value = "/uploadStaffExcel")
    public ResponseEntity<String> uploadStaffExcel(@RequestPart("file") @Parameter(description = "要上传的Excel文件") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("文件不能为空");
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

            return ResponseEntity.ok("Excel文件上传并解析成功" + staffInfoEntityList);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("解析Excel文件时发生错误：" + e.getMessage());
        }
    }
}
