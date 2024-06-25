package com.example.demo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 请求的入参实体
 * description: xxx
 * date: 2023-03-23 11:24
 * author: duyx
 * version: 1.0
 */
@Data
@Component
public class InvoiceDataEntity {

    @NotNull(message = "类型不能为空")
    private String type;

    @NotNull(message = "正负票标志不能为空")
    private Integer redBillFlag;

    @NotNull(message = "票据号不能为空")
    private String serialno;

    private String optcode;

    private String optname;

    private String opttime;

    private String strikedserialno;

    private String isPaySerialNo;

    private String isMadeQrCode;

    private String hisBillNumber;

    private String hisbillcode;

    private String issueDate;

    private String orgcode;

    private String relatedticketnum;

    private String invoiceno;

    private String sources;

    private String agencysign;

    private String remark;

}
