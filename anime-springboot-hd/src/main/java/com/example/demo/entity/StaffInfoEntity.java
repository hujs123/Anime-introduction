package com.example.demo.entity;
import javax.persistence.*;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author hujs
 * @date 2024/06/28
 * @description 干员信息表
 */
@Entity
@Data
@ToString
@Table(name = "staff_info")
public class StaffInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "干员id(自增）")
    private Integer id;

    @Column(name = "person_id",columnDefinition = "人员id")
    private Integer personId;

    @Column(name = "name", nullable = false, columnDefinition = "姓名")
    private String name;

    @Column(name = "gender", columnDefinition = "性别")
    private String gender;

    @Column(name = "ethnicity", columnDefinition = "种族")
    private String ethnicity;

    @Column(name = "star", columnDefinition = "星级")
    private String star;

    @Column(name = "create_by", columnDefinition = "创建人")
    private String createBy;

    @Column(name = "create_time", columnDefinition = "创建时间")
    private Date createTime;

    @Column(name = "update_by", columnDefinition = "更新人")
    private String updateBy;

    @Column(name = "update_time", columnDefinition = "更新时间")
    private Date updateTime;

    @Column(name = "remark", columnDefinition = "备注")
    private String remark;
}
