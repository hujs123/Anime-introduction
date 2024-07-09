package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hujs
 * @date 2024/07/03
 * @description 字典表
 */
@Entity
@Data
@ToString
@Table(name = "dict")
public class DictEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dict_id",columnDefinition = "字典id(自增）")
    private Integer dictId;

    @Column(name = "dict_name", columnDefinition = "参数名称")
    private String dictName;

    @Column(name = "dict_type", columnDefinition = "参数类型")
    private String dictType;

    @Column(name = "dict_value", columnDefinition = "参数键名")
    private String dictValue;

    @Column(name = "dict_key", columnDefinition = "参数键值")
    private String dictKey;

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
