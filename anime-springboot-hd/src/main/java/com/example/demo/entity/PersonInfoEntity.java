package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hujs
 * @date 2024/06/28
 * @description
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

    @Column(name = "age", columnDefinition = "年龄")
    private Integer age;

    @Column(name = "gender", columnDefinition = "性别")
    private String gender;

    @Column(name = "ethnicity", columnDefinition = "种族")
    private String ethnicity;

    @Column(name = "position", columnDefinition = "职位")
    private String position;

    @Column(name = "tags", columnDefinition = "标签")
    private String tags;

    @Column(name = "organize", columnDefinition = "组织")
    private String organize;

    @Column(name = "gmt_create", columnDefinition = "标签")
    private Date gmtCreate;

    @Column(name = "gmt_modified", columnDefinition = "标签")
    private Date gmtModified;



}
