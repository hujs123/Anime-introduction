package com.example.demo.thirdInterface;

import org.apache.commons.lang3.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.Collections;

/**
 *
 */
@Slf4j
@Component
public class thireInterface {

    @Autowired
    private RestTemplate restTemplate;
    /**
     * 发送HTTP请求
     * @param method 方法名
     * @param url 地址
     * @param inputJson 参数
     * @return
     */
    public String thirdServerInterface(String method, String url, String inputJson) {

         String newurl="http://172.30.10.31:7300/mock/652396bf63eb8c002142ec43/mockmain/inhosPayBill";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
        HttpEntity<String> strEntity = new HttpEntity(inputJson, headers);
        String outJson = restTemplate.postForObject(url, strEntity, String.class);
        log.info("----------------------------------------------------------");
        log.info(StringUtils.join(Collections.singleton("调用平台端接口,出参:"), outJson));

        return outJson;
    }
}
