package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.dto.StaffReqDto;
import com.example.demo.entity.PersonInfoEntity;
import com.example.demo.entity.StaffInfoEntity;
import com.example.demo.mapper.StaffMapper;
import com.example.demo.service.impl.StaffRepository;
import com.example.demo.service.impl.StaffTagAssociationsRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffMapper staffMapper;

    @Autowired
    private StaffTagAssociationsRepository staffTagAssociationsRepository;


    public ReturnData getPageStaff(String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        String name = jsonObject.getString("name");
        String gender = jsonObject.getString("gender");
        String ethnicity = jsonObject.getString("ethnicity");
        String isStaff = jsonObject.getString("isStaff");
        String position = jsonObject.getString("position");
        JSONArray jsonArray = jsonObject.getJSONArray("tags");
        log.info("【jsonArray】:" + jsonArray);
        List<Integer> integerList = new ArrayList<>();
        List<Integer> tagList = new ArrayList<>();
        //
        if (jsonArray != null && jsonArray.size() > 0) {
            tagList = JSONObject.parseArray(jsonArray.toJSONString(), Integer.class);
        }
        //
        if(position!=""&&position!=null){
            tagList.add(Integer.valueOf(position));
        }
        if(tagList.size()>0){
            integerList = staffTagAssociationsRepository.findStaffInfoByAllTagsNative(tagList, tagList.size());
            log.info("【integerList】:" + integerList);
            if(integerList.size()==0){
                return ReturnData.ok();
            }
        }
        Integer pageNum = Integer.valueOf(jsonObject.getInteger("pageNum"));
        Integer pageSize = Integer.valueOf(jsonObject.getInteger("pageSize"));
        if (pageNum.equals(null)) {
            pageNum = 1;
        }
        if (pageSize.equals(null)) {
            pageNum = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<StaffInfoEntity> personInfoEntityList = staffMapper.getPageStaff(integerList, name, gender, ethnicity, isStaff);
        log.info("【getPageCity】" + personInfoEntityList);
        PageInfo<StaffInfoEntity> pageInfo = new PageInfo<>(personInfoEntityList, pageSize);
        log.info("【getPageCity】" + pageInfo);
        return ReturnData.ok(pageInfo);
    }


    public ReturnData updateStaff(String jsonString) {
        //接收信息
        //id，人员id,姓名，性别、种族、星级、标签、职阶
        JSONObject jsonObject = JSON.parseObject(jsonString);
        String id = jsonObject.getString("id");
        String person_id = jsonObject.getString("person_id");
        String name = jsonObject.getString("name");
        String gender = jsonObject.getString("gender");
        String ethnicity = jsonObject.getString("ethnicity");
        String star = jsonObject.getString("star");
        JSONArray tagsList = jsonObject.getJSONArray("tagsList");
        //根据id删除干员标签关联表

        //查询标签列表，根据标签插入干员关联表

        //根据人员id修改人员表

        //修改干员表

        log.info("【addStaff】：存储数据： - {}");
        return ReturnData.ok("添加干员成功");
    }

    public ReturnData getStaff(StaffReqDto staffReqDto) {
        log.info("【getStaff】：查询入参： - {}", staffReqDto);
        List<StaffInfoEntity> staffInfoEntityList = staffMapper.queryStaffs(staffReqDto.getName());
        log.info("【getStaff】：出参 - {}", staffInfoEntityList);
        return ReturnData.ok(staffInfoEntityList);
    }

    public ReturnData getAllStaffs() {
        List<StaffInfoEntity> staffInfoEntityList = staffRepository.findAll();
        log.info("【getAllStaffs】：出参 - {}", staffInfoEntityList);
        return ReturnData.ok(staffInfoEntityList);
    }

    public ReturnData getStaffByName(String name) {
        List<StaffInfoEntity> staffInfoEntityList = staffRepository.findByNameContaining(name);
        log.info("【getStaffByName】：出参 - {}", staffInfoEntityList);
        return ReturnData.ok(staffInfoEntityList);
    }

    public ReturnData addStaff(HttpServletRequest request) {
        String name = String.valueOf(request.getParameter("name"));
        String gender = String.valueOf(request.getParameter("gender"));
        String ethnicity = String.valueOf(request.getParameter("ethnicity"));
        String position = String.valueOf(request.getParameter("position"));
        String tags = String.valueOf(request.getParameter("tags"));
        String star = String.valueOf(request.getParameter("star"));

        StaffInfoEntity staffInfoEntity=new StaffInfoEntity();
        if (StringUtils.isEmpty(name)) {
            return ReturnData.error("name不可为空");
        }
        staffInfoEntity.setName(name);
        if (!StringUtils.isEmpty(gender)) {
            staffInfoEntity.setGender(gender);
        }
        if (!StringUtils.isEmpty(ethnicity)) {
            staffInfoEntity.setEthnicity(ethnicity);
        }
        if (!StringUtils.isEmpty(star)) {
            staffInfoEntity.setStar(star);
        }
        log.info("【addStaff】：存储数据： - {}", staffInfoEntity);
        staffRepository.save(staffInfoEntity); // 批量保存实体到数据库
        return ReturnData.ok("添加干员成功");
    }

};




