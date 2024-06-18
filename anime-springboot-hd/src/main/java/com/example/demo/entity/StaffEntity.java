package com.example.demo.entity;
import javax.persistence.*;
import java.util.Date;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@ToString
@Table(name = "staff")
public class StaffEntity {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id",columnDefinition = "人员id(自增）")
    private int id;

//    @Column(name = "name", nullable = false, length = 255, columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci")
    private String name;

//    @Column(name = "age", nullable = true)
    private String age;

//    @Column(name = "gender", nullable = true, length = 255, columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci")
    private String gender;

//    @Column(name = "ethnicity", nullable = true, length = 255, columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci")
    private String ethnicity;

//    @Column(name = "position", nullable = true, length = 255, columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci")
    private String position;

//    @Column(name = "tags", nullable = true, length = 255, columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci")
    private String tags;

//    @Column(name = "organize", nullable = true, length = 255, columnDefinition = "varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci")
    private String organize;

//    @Column(name = "gmt_create", nullable = true)
//    @Temporal(TemporalType.TIMESTAMP)
    private Date gmtCreate;

//    @Column(name = "gmt_modified", nullable = true, updatable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    @UpdateTimestamp
    private Date gmtModified;
}
