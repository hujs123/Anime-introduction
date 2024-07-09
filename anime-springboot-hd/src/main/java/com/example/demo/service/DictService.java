package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.entity.DictEntity;
import com.example.demo.mapper.DictMapper;
import com.example.demo.service.impl.DictRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

import static com.example.demo.config.enums.AdminMessage.admin;

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

    @Autowired
    private DictRepository dictRepository;

    public ReturnData getDict(String jsonString) {
        JSONObject jsonObject= JSON.parseObject(jsonString);
        String dictType = jsonObject.getString("dictType");
        log.info("【getDict】入参：" + dictType);
        List<DictEntity> dictEntityList = dictMapper.getDictList(dictType);
        log.info("【getDict】:出参：" + dictEntityList);
        return ReturnData.ok(dictEntityList);
    }

    public ReturnData addDict(String jsonString) {
        JSONObject jsonObject= JSON.parseObject(jsonString);
        log.info("【getDict】入参：jsonObject:"+jsonObject);
        String dict_name = jsonObject.getString("dict_name");
        String dict_type = jsonObject.getString("dict_type");
        String dict_value = jsonObject.getString("dict_value");
        String dict_key = jsonObject.getString("dict_key");
        DictEntity dictEntity=new DictEntity();
        dictEntity.setDictName(dict_name);
        dictEntity.setDictType(dict_type);
        dictEntity.setDictValue(dict_value);
        dictEntity.setDictKey(dict_key);
        dictEntity.setCreateTime(new Date());
        dictEntity.setCreateBy(admin);
        dictRepository.save(dictEntity);
        log.info("【addDict】:dictEntity："+dictEntity );
        return ReturnData.ok();
    }

    public ReturnData updateDict(String jsonString) {
        JSONObject jsonObject= JSON.parseObject(jsonString);
        log.info("【getDict】入参：jsonObject:"+jsonObject);
        Integer dict_id = jsonObject.getInteger("dict_id");
        String dict_name = jsonObject.getString("dict_name");
        String dict_type = jsonObject.getString("dict_type");
        String dict_value = jsonObject.getString("dict_value");
        String dict_key = jsonObject.getString("dict_key");
        DictEntity dictEntity=new DictEntity();
        dictEntity.setDictId(dict_id);
        dictEntity.setDictName(dict_name);
        dictEntity.setDictType(dict_type);
        dictEntity.setDictValue(dict_value);
        dictEntity.setDictKey(dict_key);
        dictEntity.setCreateTime(new Date());
        dictEntity.setCreateBy(admin);
        dictRepository.save(dictEntity);
        log.info("【addDict】:dictEntity："+dictEntity );
        return ReturnData.ok();
    }

    public ReturnData deleteDict(String jsonString) {
        JSONObject jsonObject= JSON.parseObject(jsonString);
        log.info("【deleteDict】入参：jsonObject:"+jsonObject);
        JSONArray idList = jsonObject.getJSONArray("dict_id");
        if (idList != null && idList.size() > 0) {
            List<Integer> tagList = JSONObject.parseArray(idList.toJSONString(), Integer.class);
            log.info("【deleteDict】tagList:" + tagList);
            for(Integer id: tagList){
                dictRepository.deleteById(Long.valueOf(id));
            }
        }
        return ReturnData.ok();
    }
}
