package com.example.demo.dto;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class StaffReqDto {

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 种族
     */
    private String ethnicity;

    /**
     * 职位
     */
    private String position;

    /**
     * 标签
     */
    private String tags;

    /**
     * 组织
     */
    private String organize;
}
