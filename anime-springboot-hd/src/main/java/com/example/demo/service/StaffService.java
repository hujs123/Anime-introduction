package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.dto.StaffReqDto;
import com.example.demo.dto.StaffRspDto;
import com.example.demo.config.ResultData;
import com.example.demo.entity.InvoiceDataEntity;
import com.example.demo.entity.StaffEntity;
import com.example.demo.mapper.StaffMapper;
import com.example.demo.service.impl.StaffRepository;
import com.example.demo.thirdInterface.thireInterface;
import com.example.demo.utils.EncryptionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private EncryptionUtils encryptionUtils;

    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private thireInterface thireInterface;
    @Autowired
    private RestTemplate restTemplate;

    public JSONObject jsonObject =new JSONObject();

    public List<StaffRspDto> getStaff(StaffReqDto staffReqDto) {
        log.info("【查询入参】：staffReqDto.getName() - {}", staffReqDto.getName());
        log.info("【查询入参】：staffReqDto.getName()", staffReqDto.getName());
        log.info("【查询入参】：staffReqDto.getClass().toString() - {}", staffReqDto.getClass().toString());
        log.info("【查询入参】：staffReqDto - {}", staffReqDto);
        List<StaffEntity> staffEntityList = staffMapper.queryStaffs(staffReqDto.getName());
        log.info("【查询出参】：StaffReqDto - {}", staffEntityList);
        List<StaffEntity> staffEntityList2 = staffRepository.findByNameContaining(staffReqDto.getName());
        log.info("【查询出参】：staffEntityList2 - {}", staffEntityList2);
        List<StaffRspDto> staffRspDtoList = new ArrayList<>();
        for (StaffEntity staffEntity : staffEntityList) {
            StaffRspDto staffRspDto = new StaffRspDto();
            staffRspDto.setId(staffEntity.getId());
            staffRspDto.setName(staffEntity.getName());
            staffRspDto.setGender(staffEntity.getGender());
            staffRspDto.setAge(staffEntity.getAge());
            staffRspDto.setEthnicity(staffEntity.getEthnicity());
            staffRspDto.setPosition(staffEntity.getPosition());
            staffRspDto.setOrganize(staffEntity.getOrganize());
            staffRspDtoList.add(staffRspDto);
        }
        log.info("【出参】：StaffReqDto - {}", staffRspDtoList);
        return staffRspDtoList;
    }

    ;

    public List<StaffEntity> getAllStaffs() {
        log.info("出参：111");
        List<StaffEntity> staffEntityList = staffRepository.findAll();
        log.info("出参：222" + staffEntityList);
        return staffEntityList;
    }

    ;

    public StaffEntity getStaffByName(String name) {
        log.info("【入参】：StaffReqDto - {}", name);
        StaffEntity staffEntity = staffRepository.findByName(name);
        log.info("【出参】：staffEntity - {}", staffEntity);
        StaffEntity staffEntity1 = staffRepository.findById(1);
        log.info("【出参】：staffEntity - {}", staffEntity1);
        List<StaffEntity> staffEntity2 = staffRepository.findAll();
        log.info("【出参】：staffEntity - {}", staffEntity2);
        return staffEntity;
    }

    ;


    public String getPixivs(String name, Integer num, String keyword) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String url = "https://image.anosu.top/pixiv/json";
//        String url="https://health.neuqsoft.com/zzjpay/H13010400915/v1/einvoice/uploadEInvoice";

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
        // 请求成功，处理响应体
        if (statusCode == HttpStatus.OK) {
            // 请求成功，处理响应体
            log.info("【statusCode】： - {}", statusCode);
            String body = ResponseData.getBody();
            log.info("【body】： - {}", body);
            HttpHeaders headers2 = ResponseData.getHeaders();
            log.info("【headers2】： - {}", headers2);
        } else {
            // 请求失败，处理错误情况
            log.info("【statusCode】： - {}", statusCode);
            log.info("【ResponseData】： - {}", ResponseData);
            log.info("【msg】： - {}", "获取返回值失败");
            // 例如，你可以通过 responseEntity.getBody() 获取错误消息（如果有的话）
        }
        return "OK";
    }

    ;

    public String getdzfp() throws JAXBException, XMLStreamException {
        MultiValueMap<String, String> formData2 = new LinkedMultiValueMap<>();
        RestTemplate restTemplate = new RestTemplate();
        //解决返回xml格式中文乱码问题
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(formData, headers);

//        String url="https://health.neuqsoft.com/zzjpay/H13010400915/v1/einvoice/uploadEInvoice";
        String url = "https://health.neuqsoft.com/zzjpay/H13010400915/v1/einvoice/queryEInvoiceState";


        log.info("【url】：url - {}", url);
        //构建表单数据
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("einvoicecode", "13061024");
        formData.add("einvoicenumber", "0108121995");
//        formData.add("eticketnum", "1301043610170001");

        // 检查参数是否满足接口要求（至少有一项不为空）
        if (formData.isEmpty()) {
            throw new IllegalArgumentException("At least one of 'einvoicecode/einvoicenumber' or 'eticketnum' must be non-null.");
        }

        StringWriter writer = new StringWriter();
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlWriter = outputFactory.createXMLStreamWriter(writer);
        // 写入XML声明
        xmlWriter.writeStartDocument("UTF-8", "1.0");

        // 写入根元素
        xmlWriter.writeStartElement("result");

        // 写入子元素
        xmlWriter.writeStartElement("code");
        xmlWriter.writeCharacters("0");
        xmlWriter.writeEndElement();
        // data元素可能为空，但仍然需要写入开始和结束标签
        xmlWriter.writeStartElement("data");
        xmlWriter.writeEndElement();

        // 写入message元素
        xmlWriter.writeStartElement("message");
        xmlWriter.writeCharacters("日对账调用成功");
        xmlWriter.writeEndElement();

        // 写入根元素的结束标签
        xmlWriter.writeEndElement();

        // 完成文档
        xmlWriter.writeEndDocument();
        // 刷新并关闭writer，然后返回生成的XML字符串
        writer.flush();
        log.info("【writer】： - {}", writer.toString());


        String ResponseData = restTemplate.postForObject(url, formData, String.class);
        log.info("【ResponseData】： - {}", ResponseData);
        // 使用JAXB解析XML字符串
        JAXBContext context = JAXBContext.newInstance(ResultData.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(ResponseData);
        ResultData result = (ResultData) unmarshaller.unmarshal(reader);
        log.info("【result】： - {}", result);

//        ResponseEntity<String> ResponseData = restTemplate.getForEntity(url, String.class);
        log.info("【result】： - {}", result);
        log.info("【msg】： - {}", result.getMessage());
        log.info("【data】： - {}", result.getData());
        return "OK";
    }    ;
    public String test(InvoiceDataEntity invoiceDataEntity){
//        JSONArray jsonbills=new JSONArray();
//        JSONObject jsondata=new JSONObject();
//        JSONObject jsonObject1=new JSONObject();
//        jsonObject1.put("einvoiceid","0006200000-02016031");
//        jsonObject1.put("einvoicecode","13061020");
//        jsonObject1.put("einvoicenumber","0000026000");
//        jsonObject1.put("eticketnum","20200814002565");
//
//        JSONObject jsonObject2=new JSONObject();
//        jsonObject2.put("einvoiceid","222");
//        jsonObject2.put("einvoicecode","222");
//        jsonObject2.put("einvoicenumber","222");
//        jsonObject2.put("eticketnum","222");
//        jsonObject.put("code","0");
//        jsonObject.put("data",jsondata);
//        jsonObject.put("message","成功");
//
//        jsondata.put("bills",jsonbills);
//        jsonbills.add(jsonObject1);
//        jsonbills.add(jsonObject2);
//        log.info("【jsonObject】： - {}", jsonObject);
//        String strdata= String.valueOf(jsonObject);
//        log.info("【strdata】： - {}", strdata);
//        JSONObject resultJson = JSONObject.parseObject(strdata);
//        log.info("【resultJson】： - {}", resultJson);
//        JSONObject jsonObject3=(JSONObject) resultJson.get("data");
//        log.info("【jsonObject3】： - {}", jsonObject3);
//        JSONArray jsonArray = (JSONArray) jsonObject3.get("bills");
//        log.info("【jsonArray】： - {}", jsonArray);
////        JSONObject jsonObject4 = (JSONObject) jsonObject3.get("bills");
////        log.info("【jsonObject4】： - {}", jsonObject4);
//        List<Object> objectList=JSONObject.parseArray(jsonArray.toJSONString(), Object.class);
//        log.info("【objectList】： - {}", objectList);


        InvoiceDataEntity invoiceDataEntity1=new InvoiceDataEntity();
        invoiceDataEntity1.setSerialno(invoiceDataEntity.getSerialno());
        invoiceDataEntity1.setType(invoiceDataEntity.getType());

        invoiceDataEntity1.setRedBillFlag(1);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("type", "13061024");
        formData.add("redBillFlag", "0118763999");
        formData.add("serialno", "130104361017202406211551460001");
        formData.add("optcode", "473870");
        formData.add("optname", "130610240118763999");
        formData.add("opttime", "测试");
        formData.add("strikedserialno", "20240621");
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        HttpEntity<String> strEntity = new HttpEntity<>(invoiceDataEntity1.toString(), headers);
        String outJson = restTemplate.postForObject("http://localhost:9088/dzpj/xhkj/getInvoiceFiles", formData, String.class);
        log.info("【outJson】： - {}", outJson);
        return "ok";
    };


    public void test2(String value,HttpServletResponse response )  {
        String strurl="http://172.30.10.31:7300/mock/652396bf63eb8c002142ec43/mockmain/getbase64";
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("einvoicetype", "");
        String ResponseData = restTemplate.postForObject(strurl, formData, String.class);
        String strobj= (String) JSONObject.parseObject(ResponseData).get("data");
//        Result result= Result.ok(strobj);
        ReturnData result= ReturnData.ok(strobj);
        log.info("【result】：result"+result.toString());
//        log.info("【test2】：strobj"+strobj);
        ByteArrayInputStream bais=null;
        try{
//            String str=strobj;
            String str=(String) result.getData();
            log.info("str"+str);
            //将Base64解码成字节数组
            byte[] bytes = Base64.getDecoder().decode(str);
            log.info("获取图片serivice出参bytes"+bytes);
            //构建字节数组输入流
            bais = new ByteArrayInputStream(bytes);
            log.info("获取图片serivice出参bytes"+bytes);
            //通过ImageIO将字节输入流转化为BufferedImage
            BufferedImage bufferedImage=ImageIO.read(bais);
            //写入到输出流
            ImageIO.write(bufferedImage,"png",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        } finally {
            try {
                if(bais!=null){
                    //关闭流
                    bais.close();
                }
            }catch (IOException e){
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
    }






};




