package com.example.demo.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author hujs
 * @date 2024/07/05
 * @description 干员标签关联表
 */
@Entity
@Data
@ToString
@Table(name = "staff_tag_associations")
public class StaffTagAssociationsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "id(自增）")
    private Integer id;

    @Column(name = "staff_id", columnDefinition = "干员id")
    private Integer staffId;

    @Column(name = "staff_name", columnDefinition = "干员姓名")
    private String staffName;

    @Column(name = "tag_id", columnDefinition = "标签id")
    private Integer tagId;

    @Column(name = "tag_name", columnDefinition = "标签名称")
    private String tagName;

    @Column(name = "create_by", columnDefinition = "创建人")
    private String createBy;

    @Column(name = "create_time", columnDefinition = "创建时间")
//    @Temporal(TemporalType.TIMESTAMP) // 指定日期/时间类型
    private Date createTime;
}
