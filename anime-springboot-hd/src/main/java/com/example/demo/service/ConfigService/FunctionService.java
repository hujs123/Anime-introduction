package com.example.demo.service.ConfigService;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.entity.StaffInfoEntity;
import com.example.demo.mapper.StaffMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

import static com.example.demo.config.enums.ThirdInterfaceEnums.Third_Interface_EinvoicingBase64;
import static com.example.demo.config.enums.ThirdInterfaceEnums.Third_Interface_Province;

/**
 * @author hujs
 * @date 2024/07/22
 * @description
 */
@Slf4j
@Service
public class FunctionService {

    @Value("${minio.bucket}")
    private String bucketname;

    @Autowired
    private MinioClient minioClient;

    public String uploadFile(MultipartFile file) {
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
            return uniqueObjectName;
        } catch (IOException e) {
            // 处理异常
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
