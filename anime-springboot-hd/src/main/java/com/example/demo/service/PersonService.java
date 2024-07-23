package com.example.demo.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.constant.ConstantEnum;
import com.example.demo.entity.PersonInfoEntity;
import com.example.demo.entity.StaffInfoEntity;
import com.example.demo.entity.StaffTagAssociationsEntity;
import com.example.demo.entity.StaffTagEntity;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.service.ConfigService.FunctionService;
import com.example.demo.service.impl.PersonRepository;
import com.example.demo.service.impl.StaffRepository;
import com.example.demo.service.impl.StaffTagAssociationsRepository;
import com.example.demo.service.impl.StaffTagRepository;
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
    private FunctionService functionService;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private StaffTagAssociationsRepository staffTagAssociationsRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private StaffTagRepository staffTagRepository;

    public ReturnData getPagePerson(String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        String name = jsonObject.getString("name");
        String gender = jsonObject.getString("gender");
        String ethnicity = jsonObject.getString("ethnicity");
        String isStaff = jsonObject.getString("isStaff");
        JSONArray jsonArray = jsonObject.getJSONArray("tags");
        log.info("【jsonArray】:" + jsonArray);
        List<Integer> integerList = new ArrayList<>();
        if (jsonArray != null && jsonArray.size() > 0) {
            List<Integer> tagList = JSONObject.parseArray(jsonArray.toJSONString(), Integer.class);
            integerList = staffTagAssociationsRepository.findStaffInfoByAllTagsNative(tagList, tagList.size());
            log.info("【integerList】:" + integerList);
            if (integerList.size() == 0) {
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
        List<PersonInfoEntity> personInfoEntityList = personMapper.getPagePerson(integerList, name, gender, ethnicity, isStaff);
        log.info("【getPageCity】" + personInfoEntityList);
        PageInfo<PersonInfoEntity> pageInfo = new PageInfo<>(personInfoEntityList, pageSize);
        log.info("【getPageCity】" + pageInfo);
        return ReturnData.ok(pageInfo);
    }

    @Transactional
    public ReturnData addPersonInfo(String jsonString) {
        //接收数据
        JSONObject jsonObject = JSON.parseObject(jsonString);
        String name = jsonObject.getString("name");
        String gender = jsonObject.getString("gender");
        String ethnicity = jsonObject.getString("ethnicity");
        String isStaff = jsonObject.getString("isStaff");
        String star = jsonObject.getString("star");
        JSONArray tagsList = jsonObject.getJSONArray("tagsList");
        //查找是否有相同名字人员
        log.info("【addPersonInfo】personInfoEntity:" + name);
        PersonInfoEntity personInfoEntity = personRepository.findByName(name);
        log.info("【addPersonInfo】personInfoEntity:" + personInfoEntity);
        if (personInfoEntity!=null) {
            return ReturnData.error("已包含姓名为: '" + name + "' 的人员。");
        }
        //存储人员信息
        PersonInfoEntity personInfoEntity1 = new PersonInfoEntity();
        personInfoEntity1.setName(name);
        personInfoEntity1.setGender(gender);
        personInfoEntity1.setEthnicity(ethnicity);
        personInfoEntity1.setIsStaff(isStaff);
        personInfoEntity1.setCreateTime(new Date());
        personInfoEntity1.setCreateBy(admin);
        log.info("【addPersonInfo】personInfoEntity1:" + personInfoEntity1);
        PersonInfoEntity personInfoEntity2 = personRepository.save(personInfoEntity1);
        log.info("【addPersonInfo】personInfoEntity1:" + personInfoEntity2);
        //若添加人员是干员
        if (isStaff.equals(ConstantEnum.PersonType.ISSTAFF)) {
            //存储干员信息
            StaffInfoEntity staffInfoEntity = new StaffInfoEntity();
            staffInfoEntity.setPersonId(personInfoEntity2.getId());
            staffInfoEntity.setName(name);
            staffInfoEntity.setGender(gender);
            staffInfoEntity.setEthnicity(ethnicity);
            staffInfoEntity.setStar(star);
            staffInfoEntity.setCreateTime(new Date());
            staffInfoEntity.setCreateBy(admin);
            log.info("【addPersonInfo】staffInfoEntity:" + staffInfoEntity);
            StaffInfoEntity staffInfoEntity1 = staffRepository.save(staffInfoEntity);
            // 注意：这里假设staffRepository.save()会立即返回带有ID的实体，或者findByName能正确返回
            if (tagsList.size() > 0) {
                // 将 JSONArray 转换为 List<Integer>
                List<Integer> integerList = new ArrayList<>();
                for (int i = 0; i < tagsList.size(); i++) {
                    try {
                        integerList.add(tagsList.getInteger(i));
                    } catch (JSONException e) {
                        // 处理非字符串元素的情况（在这个例子中我们假设所有元素都是字符串）
                        e.printStackTrace();
                    }
                }
                List<StaffTagEntity> staffTagEntityList = staffTagRepository.findByTagList(integerList);
                //存储干员标签关联信息
                List<StaffTagAssociationsEntity> staffTagAssociationsEntityList = new ArrayList<>();
                for (StaffTagEntity staffTagEntity : staffTagEntityList) {
                    StaffTagAssociationsEntity staffTagAssociationsEntity = new StaffTagAssociationsEntity();
                    staffTagAssociationsEntity.setTagId(Integer.valueOf(staffTagEntity.getId()));
                    staffTagAssociationsEntity.setTagName(staffTagEntity.getParamValue());
                    staffTagAssociationsEntity.setStaffId(staffInfoEntity1.getId());
                    staffTagAssociationsEntity.setStaffName(staffInfoEntity1.getName());
                    staffTagAssociationsEntity.setCreateBy(admin);
                    staffTagAssociationsEntity.setCreateTime(new Date());
                    staffTagAssociationsEntityList.add(staffTagAssociationsEntity);
                }
                log.info("【addPersonInfo】staffTagAssociationsEntityList:" + staffTagAssociationsEntityList);
                staffTagAssociationsRepository.saveAll(staffTagAssociationsEntityList);
            }
        }
        return ReturnData.ok();
    }


    public ReturnData updatePersonInfo(String jsonString) {
        log.info("【addPersonInfo】jsonString:" + jsonString);
        JSONObject jsonObject = JSON.parseObject(jsonString);
        Integer id = jsonObject.getInteger("id");
        String name = jsonObject.getString("name");
        String gender = jsonObject.getString("gender");
        String ethnicity = jsonObject.getString("ethnicity");
        String isStaff = jsonObject.getString("isStaff");
        String star = jsonObject.getString("star");
        JSONArray tagsList = jsonObject.getJSONArray("tagsList");
        if (id.equals(null) || id.equals("")) {
            return ReturnData.error("id为空");
        }
        //更新人员信息
        PersonInfoEntity personInfoEntity = new PersonInfoEntity();
        personInfoEntity.setId(id);
        personInfoEntity.setName(name);
        personInfoEntity.setGender(gender);
        personInfoEntity.setEthnicity(ethnicity);
        personInfoEntity.setIsStaff(isStaff);
        personInfoEntity.setUpdateTime(new Date());
        personInfoEntity.setUpdateBy(admin);
        //更新人员表
        personRepository.save(personInfoEntity);
        //如果存储人员是干员
        if (isStaff.equals(ConstantEnum.PersonType.ISSTAFF)) {
            //查询干员信息表
            StaffInfoEntity staffInfoEntity = staffRepository.findByPersonId(id);
            //更新干员表
            staffInfoEntity.setPersonId(id);
            staffInfoEntity.setName(name);
            staffInfoEntity.setGender(gender);
            staffInfoEntity.setEthnicity(ethnicity);
            staffInfoEntity.setStar(star);
            if (staffInfoEntity.equals(null)) {
                staffInfoEntity.setUpdateTime(new Date());
                staffInfoEntity.setUpdateBy(admin);
            } else {
                staffInfoEntity.setCreateTime(new Date());
                staffInfoEntity.setCreateBy(admin);
            }
            staffRepository.save(staffInfoEntity);
            log.info("【addPersonInfo】staffInfoEntity:" + staffInfoEntity);
            //删除对应干员所有标签关联信息
            staffTagAssociationsRepository.deleteAllByStaffId(staffInfoEntity.getId());
            //存储干员标签关联信息
            List<StaffTagAssociationsEntity> staffTagAssociationsEntityList = new ArrayList<>();
            for (Object tagId : tagsList) {
                StaffTagAssociationsEntity staffTagAssociationsEntity = new StaffTagAssociationsEntity();
                staffTagAssociationsEntity.setTagId(Integer.valueOf(tagId.toString()));
                staffTagAssociationsEntity.setStaffId(staffInfoEntity.getId());
                staffTagAssociationsEntity.setCreateBy(admin);
                staffTagAssociationsEntity.setCreateTime(new Date());
                staffTagAssociationsEntityList.add(staffTagAssociationsEntity);
            }
            log.info("【addPersonInfo】staffTagAssociationsEntityList:" + staffTagAssociationsEntityList);
            staffTagAssociationsRepository.saveAll(staffTagAssociationsEntityList);
        } else {
            //查询干员信息表
            StaffInfoEntity staffInfoEntity = staffRepository.findByPersonId(id);
            if (!staffInfoEntity.equals(null)) {
                //删除对应干员所有标签关联信息
                staffTagAssociationsRepository.deleteAllByStaffId(staffInfoEntity.getId());
                //存储干员标签关联信息
                List<StaffTagAssociationsEntity> staffTagAssociationsEntityList = new ArrayList<>();
                for (Object tagId : tagsList) {
                    StaffTagAssociationsEntity staffTagAssociationsEntity = new StaffTagAssociationsEntity();
                    staffTagAssociationsEntity.setTagId(Integer.valueOf(tagId.toString()));
                    staffTagAssociationsEntity.setStaffId(staffInfoEntity.getId());
                    staffTagAssociationsEntity.setCreateBy(admin);
                    staffTagAssociationsEntity.setCreateTime(new Date());
                    staffTagAssociationsEntityList.add(staffTagAssociationsEntity);
                }
                //删除干员表信息
                staffRepository.deleteById(staffInfoEntity.getId());
            }
        }
        log.info("【addPersonInfo】personInfoEntity:" + personInfoEntity);
        return ReturnData.ok();
    }

    public ReturnData deletePersonInfo(String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        String id = jsonObject.getString("id");
        personRepository.deleteById(Long.valueOf(id));
        return ReturnData.ok();
    }

    public ReturnData uploadPersonsInfo(String jsonString) {
        JSONObject jsonObject = JSON.parseObject(jsonString);
        String id = jsonObject.getString("id");
        personRepository.deleteById(Long.valueOf(id));
        return ReturnData.ok();
    }


}
