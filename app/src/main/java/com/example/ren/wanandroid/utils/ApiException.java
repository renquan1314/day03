package com.example.ren.wanandroid.utils;

/**
 * 项目名：MyApplication
 * 包名：  com.notice.liangxq.http
 * 文件名：ApiException
 * 创建者：liangxq
 * 创建时间：2019/10/23  1:55
 * 描述：TODO
 */
public class ApiException extends Throwable{
    private int code;
    private String message;

    public ApiException(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
