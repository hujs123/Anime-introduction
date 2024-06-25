package com.example.demo.config.enums;

import lombok.Data;

import java.io.Serializable;

/**
 * @author nanhs
 * @version 1.0
 * @date 2023-03-24 10:17
 * @description ReturnDataYJ
 */
@Data
public class ReturnData implements Serializable {

    /**
     * 状态码
     */
    private String code;
    /**
     * 状态提示信息
     */
    private String msg;

    /**
     * 返回数据集合
     */
    private Object data;

    public ReturnData() {
        this.code = "0";
    }

    public static ReturnData error(){
        ReturnData data = new ReturnData();
        data.setCode(ResultEnum.ERROR.getCode());
        data.setMsg(ResultEnum.ERROR.getMsg());
        return data;
    }

    public static ReturnData error(String msg){
        ReturnData data = new ReturnData();
        data.setCode(ResultEnum.ERROR.getCode());
        data.setMsg(msg);
        return data;
    }

    public static ReturnData error(String code,String msg){
        ReturnData data = new ReturnData();
        data.setCode(code);
        data.setMsg(msg);
        return data;
    }

    public static ReturnData error(String code,String msg,Object obj){
        ReturnData data = new ReturnData();
        data.setCode(code);
        data.setMsg(msg);
        data.setData(obj);
        return data;
    }

    public static ReturnData result(String code,String msg){
        ReturnData data = new ReturnData();
        data.setCode(code);
        data.setMsg(msg);
        return data;
    }

    public static ReturnData ok(Object obj){
        ReturnData data = new ReturnData();
        data.setCode(ResultEnum.SUCCESS.getCode());
        data.setMsg(ResultEnum.SUCCESS.getMsg());
        data.setData(obj);
        return data;
    }

    public static ReturnData ok(String code,String msg){
        ReturnData data = new ReturnData();
        data.setCode(code);
        data.setMsg(msg);
        return data;
    }

    public static ReturnData ok(String code,String msg,Object obj){
        ReturnData data = new ReturnData();
        data.setCode(code);
        data.setMsg(msg);
        data.setData(obj);
        return data;
    }

    public static ReturnData ok(){
        ReturnData data = new ReturnData();
        data.setCode(ResultEnum.SUCCESS.getCode());
        data.setMsg(ResultEnum.SUCCESS.getMsg());
        return data;
    }

}

