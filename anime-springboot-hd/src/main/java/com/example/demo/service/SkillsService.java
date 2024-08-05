package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.entity.StaffInfoEntity;
import com.example.demo.mapper.StaffMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.code.kaptcha.Producer;
import com.itextpdf.text.*;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
//import io.minio.PutObjectOptions;


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

    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucket}")
    private String bucketname;

    @Autowired
    private Producer captchaProducer;

    public ReturnData callThirdInterface() {
        String strurl = Third_Interface_Province;
        String outJson = restTemplate.postForObject(strurl, null, String.class);
        JSONArray jsonArray = JSONArray.parseArray(outJson);
        log.info("【查询出参】： - {}", ReturnData.ok(jsonArray));
        return ReturnData.ok(jsonArray);
    }


    public void base64ToImage(HttpServletResponse response) {
        //单文件
//        //获取base64字符串
//        String strurl = Third_Interface_EinvoicingBase64;
//        String outJson = restTemplate.postForObject(strurl, null, String.class);
//        JSONObject jsonObject = JSONObject.parseObject(outJson);
//        log.info("【base64ToImage】： - {}", jsonObject);
//        JSONObject jsonObject2= (JSONObject) jsonObject.get("data");
//        String base64 = (String) jsonObject2.get("base64");
//
//        try {
//            //将Base64解码成字节数组
//            byte[] bytes = Base64.getDecoder().decode(base64);
//            //构建字节数组输入流
//            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
//            //通过ImageIO将字节输入流转化为BufferedImage
//            BufferedImage bufferedImage = ImageIO.read(bais);
//            //20240624 对接新禾添加（扫描二维码后显示乱码，解码问题）
//            response.setContentType("image/png");
//            //写入到输出流
//            ImageIO.write(bufferedImage, "png", response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//            log.error(e.getMessage());
//        }

        //多文件数组
        //获取base64字符串
        String strurl = Third_Interface_EinvoicingBase64;
        String outJson = restTemplate.postForObject(strurl, null, String.class);
        JSONObject jsonObject = JSONObject.parseObject(outJson);
        log.info("【base64ToImage】： - {}", jsonObject);
        JSONArray jsonArray=  (JSONArray) jsonObject.get("data");
        log.info("【jsonArray】："+ jsonArray);
        List<String> stringList=JSONArray.parseArray(jsonArray.toJSONString(),String.class);
        log.info("【stringList】："+ stringList);
        try {
            // 创建输出流
            OutputStream outputStream = response.getOutputStream();
            // 创建PDF文档对象
            Document document = new Document(PageSize.A4);
            java.io.ByteArrayOutputStream pdfOutputStream = new java.io.ByteArrayOutputStream();
            PdfWriter.getInstance(document, pdfOutputStream);
            document.open();

            // 将每个Base64图片字符串转换为图片并添加到PDF文档中
            for (String base64ImageString : stringList) {
                // 解码Base64字符串
                byte[] imageBytes = Base64.getDecoder().decode(base64ImageString);
                // 创建Image对象
                Image image = Image.getInstance(imageBytes);
                // 设置图片位置和大小（可根据需要调整）
                image.setAbsolutePosition(0, 0);
                image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
                // 添加图片到PDF文档
                document.add(image);
            }
            // 关闭文档对象
            document.close();
            // 将PDF数据写入响应流
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=dianzifapiao.pdf");
            response.setContentLength(pdfOutputStream.size());
            pdfOutputStream.writeTo(outputStream);
            // 刷新缓冲并关闭输出流
            outputStream.flush();
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public void base64ToPdf(HttpServletResponse response) {
        //获取base64字符串
        String strurl = Third_Interface_EinvoicingBase64;
        String outJson = restTemplate.postForObject(strurl, null, String.class);
        JSONObject jsonObject = JSONObject.parseObject(outJson);
        log.info("【base64ToImage】： - {}", jsonObject);
        JSONObject jsonObject2= (JSONObject) jsonObject.get("data");
        String base64 = (String) jsonObject2.get("base64");
        try{
            // 创建输出流
            OutputStream outputStream = response.getOutputStream();
            // 创建PDF文档对象
            Document document = new Document(PageSize.A4);
            java.io.ByteArrayOutputStream pdfOutputStream = new java.io.ByteArrayOutputStream();
            PdfWriter.getInstance(document, pdfOutputStream);
            document.open();
            // 解码Base64字符串
            byte[] imageBytes = Base64.getDecoder().decode(base64);
            // 创建Image对象
            Image image = Image.getInstance(imageBytes);
            // 设置图片位置和大小（可根据需要调整）
            image.setAbsolutePosition(0, 0);
            image.scaleToFit(PageSize.A4.getWidth(), PageSize.A4.getHeight());
            // 添加图片到PDF文档
            document.add(image);
            // 关闭文档对象
            document.close();
            // 将PDF数据写入响应流
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=电子发票.pdf");
            response.setContentLength(pdfOutputStream.size());
            pdfOutputStream.writeTo(outputStream);
        }catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (BadElementException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void imageToBase64(HttpServletResponse response) {
        // 假设图片位于classpath下的images目录中
        Resource resource = new ClassPathResource("result.png"); // 注意路径可能需要根据实际情况调整
        try (InputStream inputStream = resource.getInputStream()) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int bytesRead = -1;
            byte[] buffer = new byte[4096];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] bytes = outputStream.toByteArray();

            // 将字节数组转换为Base64编码的字符串
            String encodedString = Base64.getEncoder().encodeToString(bytes);
            log.info("【imageToBase64】encodedString：" + encodedString);
            // 返回Base64编码的字符串
//            return ReturnData.ok(encodedString);
            //图片转换base64
            try {
                byte[] bytes2 = Base64.getDecoder().decode(encodedString);
                //构建字节数组输入流
                ByteArrayInputStream bais = new ByteArrayInputStream(bytes2);
                //通过ImageIO将字节输入流转化为BufferedImage
                BufferedImage bufferedImage = ImageIO.read(bais);
//                // 设置响应的内容类型
//                response.setContentType("image/png");
                //写入到输出流
                ImageIO.write(bufferedImage, "png", response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        } catch (IOException e) {
            // 处理异常，例如返回错误消息
//            return ReturnData.error("Error reading the image file.");
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
        data.add(new String[]{"序号", "姓名", "性别", "种族", "职位", "标签", "星级", "新建时间", "更新时间"});
        for (StaffInfoEntity staffInfoEntity : staffInfoEntityList) {
            String[] staffData = new String[]{
                    String.valueOf(staffInfoEntity.getId()), // 转换为字符串
                    staffInfoEntity.getName(),
                    staffInfoEntity.getGender(),
                    staffInfoEntity.getEthnicity(),
                    staffInfoEntity.getStar(),
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
        headers.setContentDispositionFormData("attachment", "staff-info-" + datestr + ".xlsx");

        // 返回包含Excel内容的响应
        return ResponseEntity.ok()
                .headers(headers)
                .body(new ByteArrayResource(content));
    }

    public void uploadFile(MultipartFile file) {
        try {
            // 假设你已经有了bucketName和objectName
            String bucketName = bucketname;
            //使用UUID+文件名生成唯一对象名
            String uniqueObjectName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename(); // 假设这个方法生成一个唯一的对象名

            // 获取文件输入流
            InputStream stream = file.getInputStream();
            // 构建PutObjectArgs
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(uniqueObjectName)
                    .stream(stream, file.getSize(), -1) // 注意：这里的-1是content type的索引，如果不需要设置特定类型，可以留空或设置为null
                    .contentType(file.getContentType()) // 设置文件的MIME类型
                    .build();
            // 上传文件到MinIO
            minioClient.putObject(putObjectArgs);
            log.info("【uploadFile】：上传成功");
            // 注意：在上传完成后，你可能需要关闭流，但在这个例子中，MinioClient 会自己处理
        } catch (IOException e) {
            // 处理异常
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Resource> getFileOnMinio( @PathVariable String objectName) {
        try {
            InputStream inputStream = minioClient.getObject(GetObjectArgs.builder()
                    .bucket(bucketname)
                    .object(objectName)
                    .build());

            // 使用Spring的InputStreamResource封装流
            InputStreamResource resource = new InputStreamResource(inputStream);

            // 检查文件名是否包含非ASCII字符
            boolean containsNonAscii = !objectName.matches("^[\\x00-\\x7F]*$");

            // 设置HTTP头部信息
            HttpHeaders headers = new HttpHeaders();
            if (containsNonAscii) {
                // 使用filename*并指定字符集为UTF-8
                String encodedFilename = URLEncoder.encode(objectName, "UTF-8")
                        .replaceAll("\\+", "%20") // 替换+为%20，因为URL编码将空格替换为+
                        .replaceAll("%", "%25"); // 替换%为%25，因为%在URL中是特殊字符
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFilename);
            } else {
                // 文件名只包含ASCII字符，可以直接使用
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + objectName + "\"");
            }
            headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);

        } catch (Exception e) {
            // 异常处理
            throw new RuntimeException("Error downloading file from MinIO", e);
        }
    }

    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "no-store, no-cache");

        String capText = captchaProducer.createText();
        request.getSession().setAttribute("captcha", capText);

        BufferedImage bi = captchaProducer.createImage(capText);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        out.flush();
        out.close();
    }

    public ReturnData verifyCaptcha(@RequestParam String captcha, HttpServletRequest request) {
        String sessionCaptcha = (String) request.getSession().getAttribute("captcha");
        if (sessionCaptcha != null && sessionCaptcha.equalsIgnoreCase(captcha)) {
            log.info("【verifyCaptcha】验证码正确");
            return ReturnData.ok("验证码正确");
        } else {
            log.info("【verifyCaptcha】验证码错误");
            return ReturnData.error("验证码错误");
        }
    }
}
