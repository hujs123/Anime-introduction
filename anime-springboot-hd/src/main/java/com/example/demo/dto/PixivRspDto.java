package com.example.demo.dto;

import lombok.Data;
import lombok.ToString;

import java.util.Arrays;

@Data
@ToString
public class PixivRspDto {
    private String pid;
    private String page;
    private String uid;
    private String title;
    private String user;
    private String r18;
    private String width;
    private String height;
    private String tags;
}
