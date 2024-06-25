package com.example.demo.controller;

import com.example.demo.config.enums.ReturnData;
import com.example.demo.dto.StaffReqDto;
import com.example.demo.dto.StaffRspDto;
import com.example.demo.entity.InvoiceDataEntity;
import com.example.demo.entity.StaffEntity;
import com.example.demo.service.StaffService;
import com.example.demo.service.ThirdInterfaceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.util.List;

@Slf4j
@RestController
@Tag(name = "三方接口", description = "第三方接口")
@RequestMapping("/api/thirdInterface")
public class ThirdInterfaceController {

    @Autowired
    private ThirdInterfaceService thirdInterfaceService;

    @Operation(summary = "获取图片", description = "获取随机图片")
    @PostMapping("getPixivs")
    public ReturnData getPixivs(String name, Integer num, String keyword){
        return thirdInterfaceService.getPixivs(name,num,keyword);
    }
}
