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
public class InptuEntity {

    @NotNull(message = "类型不能为空")
    private String einvoicefile;
}
