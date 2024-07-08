package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.entity.DictEntity;
import com.example.demo.mapper.DictMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hujs
 * @date 2024/07/03
 * @description
 */
@Slf4j
@Service
public class DictService {

    @Autowired
    private DictMapper dictMapper;

    public ReturnData getDict(String jsonString) {
        JSONObject jsonObject= JSON.parseObject(jsonString);
        String dictType = jsonObject.getString("dictType");
        log.info("【getDict】入参：" + dictType);
        List<DictEntity> dictEntityList = dictMapper.getDictList(dictType);
        log.info("【getDict】:出参：" + dictEntityList);
        return ReturnData.ok(dictEntityList);
    }
}
