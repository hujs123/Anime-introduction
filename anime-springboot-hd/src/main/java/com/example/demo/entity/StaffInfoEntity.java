package com.example.demo.entity;
import javax.persistence.*;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * @author hujs
 * @date 2024/06/28
 * @description
 */
@Entity
@Data
@ToString
@Table(name = "staff_info")
public class StaffInfoEntity {
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

    @Column(name = "position", columnDefinition = "职位")
    private String position;

    @Column(name = "tags", columnDefinition = "标签")
    private String tags;

    @Column(name = "star", columnDefinition = "星级")
    private String star;

    @Column(name = "gmt_create", columnDefinition = "新建时间")
    @Temporal(TemporalType.TIMESTAMP) // 指定日期/时间类型
    private Date gmtCreate;

    @Column(name = "gmt_modified", columnDefinition = "更新时间")
    @Temporal(TemporalType.TIMESTAMP) // 指定日期/时间类型
    private Date gmtModified;
}
