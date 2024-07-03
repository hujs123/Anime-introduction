package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.entity.PersonInfoEntity;
import com.example.demo.entity.StaffInfoEntity;
import com.example.demo.mapper.PersonMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author hujs
 * @date 2024/07/03
 * @description
 */
@Slf4j
@Service
public class PersonService {

    @Autowired
    private PersonMapper personMapper;

    public ReturnData getPagePerson(String jsonString) {
        JSONObject jsonObject= JSON.parseObject(jsonString);
        String name = jsonObject.getString("name");
        String gender = jsonObject.getString("gender");
        String ethnicity = jsonObject.getString("ethnicity");
        String position = jsonObject.getString("position");
        String tags = jsonObject.getString("tags");
        String isStaff = jsonObject.getString("isStaff");
        Integer pageNum = Integer.valueOf(jsonObject.getInteger("pageNum"));
        Integer pageSize = Integer.valueOf(jsonObject.getInteger("pageSize"));
        if(pageNum.equals(null)){
            pageNum=1;
        }
        if(pageSize.equals(null)){
            pageNum=10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<PersonInfoEntity> personInfoEntityList = personMapper.getPagePerson(name,gender,ethnicity,position,tags,isStaff);
        PageInfo<PersonInfoEntity> pageInfo = new PageInfo<>(personInfoEntityList, pageSize);
        log.info("【getPageCity】" + pageInfo);
        return ReturnData.ok(pageInfo);
    }

}
