package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.entity.PersonInfoEntity;
import com.example.demo.entity.StaffInfoEntity;
import com.example.demo.entity.StaffTagAssociationsEntity;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.service.impl.PersonRepository;
import com.example.demo.service.impl.StaffRepository;
import com.example.demo.service.impl.StaffTagAssociationsRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
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
public class PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StaffTagAssociationsRepository staffTagAssociationsRepository;

    @Autowired
    private StaffRepository staffRepository;

    public ReturnData getPagePerson(String jsonString) {
        JSONObject jsonObject= JSON.parseObject(jsonString);
        String name = jsonObject.getString("name");
        String gender = jsonObject.getString("gender");
        String ethnicity = jsonObject.getString("ethnicity");
        String isStaff = jsonObject.getString("isStaff");
        JSONArray jsonArray = jsonObject.getJSONArray("tags");
        log.info("【jsonArray】:" + jsonArray);
        List<Integer> integerList=new ArrayList<>();
        if(jsonArray != null && jsonArray.size() > 0){
            List<Integer> tagList = JSONObject.parseArray(jsonArray.toJSONString(), Integer.class);
            integerList=staffTagAssociationsRepository.findStaffInfoByAllTagsNative(tagList,tagList.size());
            log.info("【integerList】:" + integerList);
        }
        Integer pageNum = Integer.valueOf(jsonObject.getInteger("pageNum"));
        Integer pageSize = Integer.valueOf(jsonObject.getInteger("pageSize"));
        if(pageNum.equals(null)){
            pageNum=1;
        }
        if(pageSize.equals(null)){
            pageNum=10;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<PersonInfoEntity> personInfoEntityList = personMapper.getPagePerson(integerList,name,gender,ethnicity,isStaff);
        log.info("【getPageCity】" + personInfoEntityList);
        PageInfo<PersonInfoEntity> pageInfo = new PageInfo<>(personInfoEntityList, pageSize);
        log.info("【getPageCity】" + pageInfo);
        return ReturnData.ok(pageInfo);
    }

    @Transactional
    public ReturnData addPersonInfo(String jsonString) {

        JSONObject jsonObject= JSON.parseObject(jsonString);
        String name = jsonObject.getString("name");
        String gender = jsonObject.getString("gender");
        String ethnicity = jsonObject.getString("ethnicity");
        String isStaff = jsonObject.getString("isStaff");
        JSONArray tagsList = jsonObject.getJSONArray("tagsList");
        String star = jsonObject.getString("star");
        //查找是否有相同名字人员
        PersonInfoEntity personInfoEntity1=personRepository.findByName(name);
        if(personInfoEntity1!=null){

        }
        //存储人员信息
        PersonInfoEntity personInfoEntity=new PersonInfoEntity();
        personInfoEntity.setName(name);
        personInfoEntity.setGender(gender);
        personInfoEntity.setEthnicity(ethnicity);
        personInfoEntity.setIsStaff(isStaff);
        personInfoEntity.setCreateTime(new Date());
        personInfoEntity.setCreateBy(admin);
        log.info("【addPersonInfo】personInfoEntity:"+personInfoEntity);
        personRepository.save(personInfoEntity);
        //若插入人员是干员，则插入干员表，关联干员标签
        if(isStaff.equals("1")){
            //存储干员信息
            StaffInfoEntity staffInfoEntity=new StaffInfoEntity();
            staffInfoEntity.setName(name);
            staffInfoEntity.setGender(gender);
            staffInfoEntity.setEthnicity(ethnicity);
            staffInfoEntity.setStar(star);
            staffInfoEntity.setCreateTime(new Date());
            staffInfoEntity.setCreateBy(admin);
            log.info("【addPersonInfo】staffInfoEntity:"+staffInfoEntity);
            staffRepository.save(staffInfoEntity);
            // 注意：这里假设staffRepository.save()会立即返回带有ID的实体，或者findByName能正确返回
            StaffInfoEntity staffInfoEntity1=staffRepository.findByName(name);
            //存储干员标签关联信息
            List<StaffTagAssociationsEntity> staffTagAssociationsEntityList=new ArrayList<>();
            for(Object tagId : tagsList){
                StaffTagAssociationsEntity staffTagAssociationsEntity=new StaffTagAssociationsEntity();
                staffTagAssociationsEntity.setTagId(Integer.valueOf(tagId.toString()));
                staffTagAssociationsEntity.setStaffId(staffInfoEntity1.getId());
                staffTagAssociationsEntity.setCreateBy(admin);
                staffTagAssociationsEntity.setCreateTime(new Date());
                staffTagAssociationsEntityList.add(staffTagAssociationsEntity);
            }
            log.info("【addPersonInfo】staffTagAssociationsEntityList:"+staffTagAssociationsEntityList);
            staffTagAssociationsRepository.saveAll(staffTagAssociationsEntityList);

        }
        return ReturnData.ok();
    }


    public ReturnData updatePersonInfo(String jsonString) {
        JSONObject jsonObject= JSON.parseObject(jsonString);
        String name = jsonObject.getString("name");
        String gender = jsonObject.getString("gender");
        String ethnicity = jsonObject.getString("ethnicity");
        String position = jsonObject.getString("position");
        String organize = jsonObject.getString("organize");
        String tags = jsonObject.getString("tags");
        String isStaff = jsonObject.getString("isStaff");
        PersonInfoEntity personInfoEntity=new PersonInfoEntity();
        personInfoEntity.setName(name);
        personInfoEntity.setGender(gender);
        personInfoEntity.setEthnicity(ethnicity);
        personInfoEntity.setIsStaff(isStaff);
        personRepository.save(personInfoEntity);
        return ReturnData.ok();
    }

    public ReturnData deletePersonInfo(String jsonString) {
        JSONObject jsonObject= JSON.parseObject(jsonString);
        String id = jsonObject.getString("id");
        personRepository.deleteById(Long.valueOf(id));
        return ReturnData.ok();
    }

    public ReturnData uploadPersonsInfo(String jsonString) {
        JSONObject jsonObject= JSON.parseObject(jsonString);
        String id = jsonObject.getString("id");
        personRepository.deleteById(Long.valueOf(id));
        return ReturnData.ok();
    }


}
