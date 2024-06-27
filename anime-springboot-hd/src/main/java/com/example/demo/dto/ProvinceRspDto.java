package com.example.demo.dto;

import lombok.Data;

/**
 * @author hujs
 * @date 2024/06/25
 * @description 省份出参
 */
@Data
public class ProvinceRspDto {

    /**城市编码*/
    private String code;

    /**城市名称*/
    private String name;

    /**城市url*/
    private String url;
}
