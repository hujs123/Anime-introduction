package com.example.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.demo.config.enums.ReturnData;
import com.example.demo.dto.StaffReqDto;
import com.example.demo.dto.StaffRspDto;
import com.example.demo.config.ResultData;
import com.example.demo.entity.InvoiceDataEntity;
import com.example.demo.entity.StaffEntity;
import com.example.demo.mapper.StaffMapper;
import com.example.demo.service.impl.StaffRepository;
import com.example.demo.thirdInterface.thireInterface;
import com.example.demo.utils.EncryptionUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.Result;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private StaffMapper staffMapper;
    public List<StaffRspDto> getStaff(StaffReqDto staffReqDto) {
        log.info("【getStaff】：查询入参： - {}", staffReqDto);
        //xml不会输出日志
        List<StaffEntity> staffEntityList = staffMapper.queryStaffs(staffReqDto.getName());
        log.info("【getStaff】：StaffReqDto - {}", staffEntityList);
        List<StaffRspDto> staffRspDtoList = new ArrayList<>();
        for (StaffEntity staffEntity : staffEntityList) {
            StaffRspDto staffRspDto = new StaffRspDto();
            staffRspDto.setId(staffEntity.getId());
            staffRspDto.setName(staffEntity.getName());
            staffRspDto.setGender(staffEntity.getGender());
            staffRspDto.setAge(staffEntity.getAge());
            staffRspDto.setEthnicity(staffEntity.getEthnicity());
            staffRspDto.setPosition(staffEntity.getPosition());
            staffRspDto.setOrganize(staffEntity.getOrganize());
            staffRspDtoList.add(staffRspDto);
        }
        log.info("【getStaff】：出参 - {}", staffRspDtoList);
        return staffRspDtoList;
    }

    ;

    public List<StaffEntity> getAllStaffs() {
        List<StaffEntity> staffEntityList = staffRepository.findAll();
        log.info("【getAllStaffs】：staffEntityList - {}", staffEntityList);
        return staffEntityList;
    }

    ;

    public List<StaffEntity> getStaffByName(String name) {
        List<StaffEntity> staffEntityList = staffRepository.findByNameContaining(name);
        log.info("【getStaffByName】：出参 - {}", staffEntityList);
        return staffEntityList;
    }

};




