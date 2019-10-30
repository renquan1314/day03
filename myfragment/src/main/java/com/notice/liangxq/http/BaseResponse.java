package com.notice.liangxq.http;

/**
 * 项目名：MyApplication
 * 包名：  com.notice.liangxq.http
 * 文件名：BaseResponse
 * 创建者：liangxq
 * 创建时间：2019/10/22  21:58
 * 描述：TODO
 */
public class BaseResponse<T> {
    public T data;
    private int errorCode;
    private String errorMsg;
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
