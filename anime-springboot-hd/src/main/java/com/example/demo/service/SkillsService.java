package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.enums.ReturnData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import static com.example.demo.config.enums.ThirdInterfaceEnums.Third_Interface_City;

/**
 * @author hujs
 * @date 2024/06/24
 * @description
 */
@Slf4j
@Service
public class SkillsService {

    public ReturnData callThirdInterface(Object object){
        try{
            // 将对象转换为字符串，但最好确保它实际上是一个可以转换为字符串的类型
            String provinceObjectString = String.valueOf(object);
            log.info("【调用接口】: 转换后的对象字符串 = {}", provinceObjectString);
            // 尝试解析 JSON 字符串
            JSONObject jsonObject = JSONObject.parseObject(provinceObjectString);
            log.info("【调用接口】: 解析后的 JSON 对象 = {}", jsonObject.toJSONString()); // 使用 toJSONString() 输出更清晰的 JSON
            // 获取 URL 和省份代码，并进行空值检查
            String url = jsonObject.getString("url"); // 使用 getString() 方法，它会抛出异常如果键不存在
            String provinceCode = jsonObject.getString("province");
            log.info("【调用接口】:url"+url);
            log.info("【调用接口】:provincecode"+provinceCode);
            String thirdUrl=url;
            if(url.isEmpty()){
                thirdUrl=Third_Interface_City;
            }
            if(provinceCode.isEmpty()){
                thirdUrl=thirdUrl+"/ASC";
            }else{
                thirdUrl=thirdUrl+"/"+provinceCode;
            }
            log.info("【调用接口】:thirdUrl"+thirdUrl);
        } catch (Exception e) {
            log.error("【调用接口】: 发生异常", e);
            // 根据需要处理异常，例如返回一个带有错误信息的 ReturnData 对象
        }
        return ReturnData.ok();
    }

}
