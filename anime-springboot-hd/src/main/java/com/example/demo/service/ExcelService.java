package com.example.demo.service;


import com.example.demo.entity.StaffInfoEntity;
import com.example.demo.service.impl.StaffRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author hujs
 * @date 2024/06/28
 * @description 用于文件下载
 */

@Service
public class ExcelService {

    @Autowired
    private StaffRepository staffRepository; // 假设你有一个用于操作数据库的JPA仓库

    public List<StaffInfoEntity> readExcelFile(MultipartFile file) throws IOException {
        List<StaffInfoEntity> staffInfoEntityList = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0); // 假设处理第一个sheet
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // 跳过标题行
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            StaffInfoEntity staffInfoEntity = new StaffInfoEntity();
            staffInfoEntity.setId(Integer.valueOf(String.valueOf(row.getCell(0))));
            staffInfoEntity.setName(String.valueOf((row.getCell(1))));
            staffInfoEntity.setGender(String.valueOf((row.getCell(1))));
            staffInfoEntity.setEthnicity(String.valueOf((row.getCell(1))));
            staffInfoEntity.setStar(String.valueOf((row.getCell(1))));
            Date date=new Date();
            staffInfoEntity.setName(String.valueOf((row.getCell(1))));
            staffInfoEntityList.add(staffInfoEntity);
        }
        workbook.close();
        return staffInfoEntityList;
    }
    public void saveToDatabase(List<StaffInfoEntity> entities) {
        staffRepository.saveAll(entities); // 批量保存实体到数据库
    }
}
