package com.example.demo.controller;

import com.example.demo.config.enums.ReturnData;
import com.example.demo.entity.StaffInfoEntity;
import com.example.demo.service.ExcelService;
import com.example.demo.service.SkillsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
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
    @PostMapping("/uploadStaffExcel")
    public ResponseEntity<?> uploadStaffExcel(@Parameter(description = "要上传的文件", required = true)
                                              @RequestParam("file") MultipartFile file) {
        //前端
//     <form method="post" action="/api/upload/excel" enctype="multipart/form-data">
//     <input type="file" name="file" required>
//        <button type="submit">上传</button>
//    </form>
        try {
            List<StaffInfoEntity> staffInfoEntityList = excelService.readExcelFile(file);
            // 假设你已经有一个服务来处理数据库操作
            excelService.saveToDatabase(staffInfoEntityList);
            return ResponseEntity.ok("Excel文件上传成功，数据已插入数据库");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("文件上传失败：" + e.getMessage());
        }
    }
}
