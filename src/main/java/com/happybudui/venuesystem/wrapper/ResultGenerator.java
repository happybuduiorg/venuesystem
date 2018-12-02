package com.happybudui.venuesystem.wrapper;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

public class ResultGenerator {

    private ResultGenerator() {
        throw new IllegalStateException("ResultGenerator class");
    }

    //success 消息构造
    //无data返回
    public static <T> ResponseResult<T> success(){
        return new ResponseResult<>(true,100,"success");
    }

    public static <T> ResponseResult<T> success(String message){
        return new ResponseResult<>(true,100,message);
    }

    //含data返回
    public static <T> ResponseResult<T> success(T data){
        return new ResponseResult<>(true,100,"success",data);
    }

    public static <T> ResponseResult<T> success(String message,T data){
        return new ResponseResult<>(true,100,message,data);
    }

    //error消息构造（一般由异常处理使用） 无data返回

    public static <T> ResponseResult<T> error(int errorCode,String message){
        return new ResponseResult<>(false,errorCode,message);
    }

    public static <T> ResponseResult<T> error(String message){
        return new ResponseResult<>(false,200,message);

    }

    public static <T> ResponseResult<T> error(int errorcode){
        return new ResponseResult<>(false,errorcode,"error!");
    }
}
