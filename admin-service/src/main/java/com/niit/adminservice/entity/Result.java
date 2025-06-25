package com.niit.adminservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: HeiLong
 * @CreateTime: 2025-06-25)
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 5570670244988035479L;
    private int code;
    private boolean succeed = false;
    private String message="执行失败！";
    private T data;

    public Result(int code,String message){
        this.code=code;
        this.message=message;
        this.data=null;
    }


    public static <T> Result<T> fail(){
        return new Result<>();
    }

    public static <T> Result<T> fail(String message){
        Result<T> result = fail();
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> fail(int code,String message){
        Result<T> result = fail();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> success(){
        Result<T> result=new Result<>();
        result.setMessage("执行成功！");
        result.setSucceed(true);
        return result;
    }

    public static <T> Result<T> success(String message){
        Result<T> result=success();
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> success(T data){
        Result<T> result = success();
        result.setData(data);
        return  result;
    }

    public static <T> Result<T> success(T data,String message){
        Result<T> result=success();
        result.setData(data);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> success(Result<T> result){
        if(result == null){
            return success();
        }else {
            result.setMessage("执行成功！");
            result.setSucceed(true);
            return result;
        }
    }

}
