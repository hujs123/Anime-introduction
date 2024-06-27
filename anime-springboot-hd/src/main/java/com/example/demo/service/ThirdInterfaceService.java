package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.ResultData;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.dto.PixivRspDto;
import lombok.extern.slf4j.Slf4j;
import org.jboss.jandex.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.example.demo.config.enums.ThirdInterfaceEnums.*;


@Slf4j
@Service
public class ThirdInterfaceService {


    /**
     *
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
        log.info("【pixivs接口】:三方接口出参："+statusCode);
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
            JSONArray jsonArray=JSONArray.parseArray(body);
            log.info("【getProvince】出参jsonArray： - {}", jsonArray);
            return ReturnData.ok(jsonArray);
        } else {
            return ReturnData.error();
        }
    }

};




