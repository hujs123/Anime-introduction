package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="user")
public class UserEntity {
    @Id
    /**
     * 名字
     */
    private String name;

    /**
     * 作者
     */
    private String author;

    /**
     * 作品
     */
    private String opus;
}
