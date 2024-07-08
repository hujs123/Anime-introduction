package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hujs
 * @date 2024/06/28
 * @description 人员信息表
 */
@Entity
@Data
@ToString
@Table(name = "person_info")
public class PersonInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "人员id(自增）")
    private Integer id;

    @Column(name = "name", nullable = false, columnDefinition = "姓名")
    private String name;

    @Column(name = "gender", columnDefinition = "性别")
    private String gender;

    @Column(name = "ethnicity", columnDefinition = "种族")
    private String ethnicity;

    @Column(name = "is_staff", columnDefinition = "是否是干员，0 否 ，1 是")
    private String isStaff;

    @Column(name = "create_by", columnDefinition = "创建人")
    private String createBy;

    @Column(name = "create_time", columnDefinition = "创建时间")
//    @Temporal(TemporalType.TIMESTAMP) // 指定日期/时间类型
    private Date createTime;

    @Column(name = "update_by", columnDefinition = "更新人")
    private String updateBy;

    @Column(name = "update_time", columnDefinition = "更新时间")
//    @Temporal(TemporalType.TIMESTAMP) // 指定日期/时间类型
    private Date updateTime;

    @Column(name = "remark", columnDefinition = "备注")
    private String remark;



}
