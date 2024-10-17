package com.example.demo.service;

import org.codehaus.xfire.client.Client;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.dto.PacsReportdto;
import com.example.demo.dto.PixivRspDto;
import com.example.demo.dto.QueryCheckDetailInputEntity;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URL;
import java.util.*;

import static com.example.demo.config.enums.ThirdInterfaceEnums.*;


@Slf4j
@Service
public class ThirdInterfaceService {

    @Autowired
    private RestTemplate restTemplate;


    /**
     * @param name
     * @param num
     * @param keyword
     * @return
     */
    public ReturnData getPixivs(String name, Integer num, String keyword) {
        RestTemplate restTemplate = new RestTemplate();
        String url = Third_Interface_Pixiv;

        if (name != null && num != null) {
            url = url + "?name=" + name;
        }
        if (num != null) {
            url = url + "&num=" + num;
        }
        if (keyword != null) {
            url = url + "&keyword=" + num;
        }
        log.info("【url】：url - {}", url);
        ResponseEntity<String> ResponseData = restTemplate.getForEntity(url, String.class);
        HttpStatus statusCode = ResponseData.getStatusCode();
        log.info("【pixivs接口】:三方接口出参：" + statusCode);
        // 请求成功，处理响应体
        if (statusCode.equals(HttpStatus.OK)) {
            // 请求成功，处理响应体
            String body = ResponseData.getBody();
            log.info("【pixivs接口】body： - {}", body);
            JSONArray jsonArray = JSONArray.parseArray(body);
            List<PixivRspDto> resultEntityList = JSONObject.parseArray(jsonArray.toJSONString(), PixivRspDto.class);
            log.info("【pixivs接口】resultEntityList： - {}", resultEntityList);
            return ReturnData.ok(resultEntityList);
        } else {
            return ReturnData.error();
        }
    }


    public ReturnData getProvince() {
        RestTemplate restTemplate = new RestTemplate();
        String url = Third_Interface_Province;
        log.info("【getProvince】：url - {}", url);
        ResponseEntity<String> ResponseData = restTemplate.getForEntity(url, String.class);
        log.info("【getProvince】：ResponseData - {}", ResponseData);
        HttpStatus statusCode = ResponseData.getStatusCode();
        // 请求成功，处理响应体
        if (statusCode.equals(HttpStatus.OK)) {
            // 请求成功，处理响应体
            String body = ResponseData.getBody();
            JSONArray jsonArray = JSONArray.parseArray(body);
            log.info("【getProvince】出参jsonArray： - {}", jsonArray);
            return ReturnData.ok(jsonArray);
        } else {
            return ReturnData.error();
        }
    }

    public ReturnData queryCheckDetail(QueryCheckDetailInputEntity queryCheckDetailInput) {
        String REPORT_URL = "http://10.19.94.21:8081/api/thirdInterface/getPacsList";
        log.info("【queryCheckDetail】入参：" + queryCheckDetailInput);
        try {
            ReturnData R = restTemplate.postForObject(REPORT_URL, queryCheckDetailInput, ReturnData.class);
            log.info("【queryCheckDetail】调用Pacs服务出参： - {}", R);
//            JSONObject reportJSON = JSONObject.parseObject(outJson);
//            if ("-1".equals(reportJSON.getString("code"))) {
//                log.info("调用Pacs库失败："+reportJSON.getString("msg"));
//                return ReturnData.error("调用Pacs库失败："+reportJSON.getString("msg"));
//            }
//            JSONArray jsonArray = (JSONArray) reportJSON.get("data");
//            List<PacsReportdto> pacsReportdtoList = JSONArray.parseArray(jsonArray.toJSONString(), PacsReportdto.class);
//            log.info("【queryCheckDetail】调用pacs出参："+pacsReportdtoList);
//            //若pdfbase64为空，pdfurl不为空时，根据pdfurl转换为pdfbase64
//            for (PacsReportdto pacsReportdto : pacsReportdtoList) {
//                if (!StringUtils.isEmpty(pacsReportdto.getPdfurl())) {
//                    if (StringUtils.isEmpty(pacsReportdto.getPdfbase64())) {
//                        // 使用RestTemplate获取图片的字节流
//                        byte[] imageData = restTemplate.getForObject(pacsReportdto.getPdfurl(), byte[].class);
//                        // 将字节流转换为Base64编码的字符串
//                        String base64Image = Base64.getEncoder().encodeToString(imageData);
//                        pacsReportdto.setPdfbase64("data:image/jpeg;base64," + base64Image);
//                    }
//                }
//            }
//            log.info("【queryCheckDetail】出参pacsReportdtoList： - {}", pacsReportdtoList);
            return R;
        } catch (Exception e) {
            log.error("【获取pacs】服务调用失败：" + e.getMessage());
            return ReturnData.error("服务调用失败");
        }
    }

    public ReturnData getPacsList(QueryCheckDetailInputEntity queryCheckDetailInput) {
        String REPORT_URL = "http://10.19.94.21:10177/lrt/hlwyy/pacs/getPacsList";
        log.info("【获取pacs】入参："
                + queryCheckDetailInput.getCheckid() + ", "
                + queryCheckDetailInput.getPatientid() + ", "
                + queryCheckDetailInput.getBegindate() + ", "
                + queryCheckDetailInput.getEnddate());

        try {
            String reportInfo = restTemplate.getForObject(REPORT_URL
                            + "?checkId=" + (queryCheckDetailInput.getCheckid() == null ? "" : queryCheckDetailInput.getCheckid())
                            + "&patientId=" + queryCheckDetailInput.getPatientid()
                            + "&begindate=" + (queryCheckDetailInput.getBegindate() == null ? "" : queryCheckDetailInput.getBegindate())
                            + "&enddate=" + (queryCheckDetailInput.getEnddate() == null ? "" : queryCheckDetailInput.getEnddate())
                    , String.class);
            log.info("【getPacsList】调用getPacsList服务出参： - {}", reportInfo);
            JSONObject reportJSON = JSONObject.parseObject(reportInfo);
            if ("-1".equals(reportJSON.getString("code"))) {
                log.info("调用Pacs库失败：" + reportJSON.getString("msg"));
                return ReturnData.error("调用Pacs库失败：" + reportJSON.getString("msg"));
            }
            JSONArray jsonArray = (JSONArray) reportJSON.get("dataList");
            List<PacsReportdto> pacsReportdtoList = JSONArray.parseArray(jsonArray.toJSONString(), PacsReportdto.class);
            log.info("【getPacsList】调用pacs出参：" + pacsReportdtoList);
            //若pdfbase64为空，pdfurl不为空时，根据pdfurl转换为pdfbase64
//            for (PacsReportdto pacsReportdto : pacsReportdtoList) {
//                if (!StringUtils.isEmpty(pacsReportdto.getPdfurl())) {
//                    if (StringUtils.isEmpty(pacsReportdto.getPdfbase64())) {
//                        // 使用RestTemplate获取图片的字节流
//                        byte[] imageData = restTemplate.getForObject(pacsReportdto.getPdfurl(), byte[].class);
//                        // 将字节流转换为Base64编码的字符串
//                        String base64Image = Base64.getEncoder().encodeToString(imageData);
//                        pacsReportdto.setPdfbase64("data:application/pdf;base64," + base64Image);
//                    }
//                }
//            }
            log.info("【getPacsList】出参pacsReportdtoList： - {}", pacsReportdtoList);
            return ReturnData.ok(pacsReportdtoList);
        } catch (Exception e) {
            log.error("【获取pacs】服务调用失败：" + e.getMessage());
            return ReturnData.error("服务调用失败");
        }
    }


    public ReturnData getWebService() {
        String wsdlPath = "http://10.19.94.21:10177/external/webService/webServiceApi/TEST006?wsdl";
        String methpath = "helloWebService";
        String requestXMl = "Webservice";
        String requestXMl2 = "<?xml version='1.0' encoding='UTF-8'?>"
                + "<SscCardData>"
                + "<requestdata>"
                + "<VERSION>1.0</VERSION>"
                + "<Userid>" + "10086" + "</Userid>"
                + "</requestdata>"
                + "</SscCardData>";
        log.info("开始");
        try {
            Client client = new Client(new URL(wsdlPath));
            String objects= client.invoke(methpath, new Object[]{requestXMl})[0] + "";
            log.info("正常结束String ：" + objects);
            return ReturnData.error();
        } catch (Exception e) {
            log.error("非正常结束", e.getMessage());
            return ReturnData.ok();
        }
    }
};




