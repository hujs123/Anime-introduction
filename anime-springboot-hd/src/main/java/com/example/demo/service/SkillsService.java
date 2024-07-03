package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.entity.StaffInfoEntity;
import com.example.demo.mapper.StaffMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.example.demo.config.enums.ThirdInterfaceEnums.Third_Interface_EinvoicingBase64;
import static com.example.demo.config.enums.ThirdInterfaceEnums.Third_Interface_Province;

/**
 * @author hujs
 * @date 2024/06/24
 * @description
 */
@Slf4j
@Service
public class SkillsService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StaffMapper staffMapper;

    public ReturnData callThirdInterface() {
        String strurl = Third_Interface_Province;
        String outJson = restTemplate.postForObject(strurl, null, String.class);
        JSONArray jsonArray = JSONArray.parseArray(outJson);
        log.info("【查询出参】： - {}", ReturnData.ok(jsonArray));
        return ReturnData.ok(jsonArray);
    }

    public void base64ToImage(HttpServletResponse response) {
        String strurl = Third_Interface_EinvoicingBase64;
        String outJson = restTemplate.postForObject(strurl, null, String.class);
        JSONObject jsonObject = JSONObject.parseObject(outJson);
        log.info("【查询出参】： - {}", ReturnData.ok(jsonObject));
        String str = (String) jsonObject.get("data");
        //将Base64解码成字节数组
        try {
            byte[] bytes = Base64.getDecoder().decode(str);
            //构建字节数组输入流
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            //通过ImageIO将字节输入流转化为BufferedImage
            BufferedImage bufferedImage = ImageIO.read(bais);
            //写入到输出流
            ImageIO.write(bufferedImage, "png", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public ReturnData httpServletRequest(HttpServletRequest request) {
        //获取表单request的参数
        Integer returnParam = Integer.valueOf(request.getParameter("inputParam"));
        log.info("【httpServletRequest】 returnParam" + returnParam);
        return ReturnData.ok(returnParam);
    }


    public ReturnData getPageCity(String pageNum, String pageSize) {
        Integer pagenum = Integer.valueOf(pageNum);
        Integer pagesize = Integer.valueOf(pageSize);
        log.info("【getPageCity】pagenum：" + pagenum);
        log.info("【getPageCity】pagesize：" + pagesize);
        PageHelper.startPage(pagenum, pagesize);
        List<StaffInfoEntity> staffInfoEntityList = staffMapper.queryAllStaffs();
        PageInfo<StaffInfoEntity> pageInfo = new PageInfo<>(staffInfoEntityList, pagesize);
        log.info("【getPageCity】" + pageInfo);
        return ReturnData.ok(pageInfo);
    }

    private static final String FILE_PATH = "c/to/your/document.pdf"; // 替换为你的文件路径

    public ResponseEntity<ByteArrayResource> getDocument() throws IOException {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd"); // 注意这里我使用了 "yyMMdd" 而不是 "yyMMdddd"，因为日期格式中没有 4 个 d 的
        String datestr = simpleDateFormat.format(date); // 使用 SimpleDateFormat 对象的 format 方法来格式化 Date 对象

        // 创建一个新的Excel工作簿和工作表
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");
        // 如果你想要自动调整某一列的宽度以适应内容
        sheet.autoSizeColumn(7); // 第二个参数是列的索引（从0开始）
        sheet.autoSizeColumn(8); // 第二个参数是列的索引（从0开始）

        List<StaffInfoEntity> staffInfoEntityList = staffMapper.queryAllStaffs();
        log.info("【getStaff】：出参 - {}", staffInfoEntityList);
        // 填充数据（这里只是示例）
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{"序号", "姓名", "性别","种族","职位","标签","星级","新建时间","更新时间"});
        for (StaffInfoEntity staffInfoEntity : staffInfoEntityList) {
            String[] staffData = new String[]{
                    String.valueOf(staffInfoEntity.getId()), // 转换为字符串
                    staffInfoEntity.getName(),
                    staffInfoEntity.getGender(),
                    staffInfoEntity.getEthnicity(),
                    staffInfoEntity.getPosition(),
                    staffInfoEntity.getTags(),
                    staffInfoEntity.getStar(),
                    simpleDateFormat.format(staffInfoEntity.getGmtCreate()),
                    simpleDateFormat.format(staffInfoEntity.getGmtModified())
            };
            data.add(staffData);
        }
        // 写入数据到工作表
        int rowNum = 0;
        for (String[] aData : data) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (String field : aData) {
                Cell cell = row.createCell(colNum++);
                cell.setCellValue(field);
            }
        }

        // 将工作簿写入到字节数组中
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // 设置HTTP响应头
        byte[] content = outputStream.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "staff-info-"+datestr+".xlsx");

        // 返回包含Excel内容的响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(new ByteArrayResource(content));
    }
}
