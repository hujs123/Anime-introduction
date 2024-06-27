package com.example.demo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.dto.ProvinceRspDto;
import com.example.demo.dto.StaffRspDto;
import com.example.demo.entity.StaffEntity;
import com.example.demo.mapper.StaffMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

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

    public ReturnData callThirdInterface() {
        String strurl = Third_Interface_Province;
        String outJson = restTemplate.postForObject(strurl, null, String.class);
        JSONArray jsonArray = JSONArray.parseArray(outJson);
        log.info("【查询出参】： - {}", ReturnData.ok(jsonArray));
        return ReturnData.ok(jsonArray);
    }

    public ReturnData base64ToImage() {
        String strurl = Third_Interface_Province;
        String outJson = restTemplate.postForObject(strurl, null, String.class);
        JSONArray jsonArray = JSONArray.parseArray(outJson);
        log.info("【查询出参】： - {}", ReturnData.ok(jsonArray));
        return ReturnData.ok(jsonArray);
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
        log.info("【getPageCity】pagenum："+pagenum);
        log.info("【getPageCity】pagesize："+pagesize);
        PageHelper.startPage(pagenum, pagesize);
        List<StaffEntity>  staffEntityList=staffMapper.queryAllStaffs();
        PageInfo<StaffEntity> pageInfo = new PageInfo<>(staffEntityList, pagesize);
        log.info("【getPageCity】"+pageInfo);
        return ReturnData.ok(pageInfo);
    }
}
