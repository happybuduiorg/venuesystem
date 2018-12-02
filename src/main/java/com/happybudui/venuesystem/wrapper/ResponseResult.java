package com.happybudui.venuesystem.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;

//CopyRight © 2018-2018 Happybudui All Rights Reserved.
//Written by Happybudui

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseResult<T> {

    private boolean success;
    private int errorcode;
    private String message;
    private T data;

    public ResponseResult() {
    }

    ResponseResult(boolean success, int errorcode,String message) {
        this.success = success;
        this.errorcode = errorcode;
        this.message = message;
    }

    ResponseResult(boolean success, int errorcode, String message,  T data) {
        this.success = success;
        this.errorcode = errorcode;
        this.message = message;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(int errorcode) {
        this.errorcode = errorcode;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

}

