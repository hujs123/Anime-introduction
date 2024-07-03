package com.example.demo.config.enums;

/**
 * @author hujs
 * @version 1.0
 * @date 2023-03-24 15:06
 * @description ResultEnum
 */

public enum ResultEnum {
    SUCCESS("0", "成功"),
    ERROR("1", "失败"),
    EXCEPTION("-1", "异常");

    private String code;
    private String msg;

    // 构造函数是私有的，仅用于枚举常量的初始化
    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    // getter方法
    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    // 可以添加其他方法，比如toString()
    @Override
    public String toString() {
        return "ResultEnum{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
