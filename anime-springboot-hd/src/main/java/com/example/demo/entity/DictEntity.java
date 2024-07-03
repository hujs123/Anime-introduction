package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @author hujs
 * @date 2024/07/03
 * @description
 */
@Entity
@Data
@ToString
@Table(name = "dict")
public class DictEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dict_id",columnDefinition = "人员id(自增）")
    private String dictId;

    @Column(name = "dict_name", columnDefinition = "职位")
    private String dictName;

    @Column(name = "dict_type", columnDefinition = "职位")
    private String dictType;

    @Column(name = "dict_value", columnDefinition = "职位")
    private String dictValue;

    @Column(name = "dict_key", columnDefinition = "职位")
    private String dictKey;

    @Column(name = "create_by", columnDefinition = "职位")
    private String createBy;

    @Column(name = "create_time", columnDefinition = "职位")
    private String createTime;

    @Column(name = "update_by", columnDefinition = "职位")
    private String updateBy;

    @Column(name = "update_time", columnDefinition = "职位")
    private String updateTime;

    @Column(name = "remark", columnDefinition = "职位")
    private String remark;

}
