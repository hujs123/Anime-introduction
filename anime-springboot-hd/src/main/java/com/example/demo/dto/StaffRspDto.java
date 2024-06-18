package com.example.demo.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StaffRspDto {
    /**id*/
    private int id;
    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String gender;

    /**
     * 年龄
     */
    private Integer age;

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
