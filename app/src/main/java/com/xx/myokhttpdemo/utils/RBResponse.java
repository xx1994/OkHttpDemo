package com.xx.myokhttpdemo.utils;

/**
 * Created by XuXiang on 2016/11/8.
 * 用于封装服务器返回的json信息。RBResponse 包含公共的response字段。
 */
public class RBResponse {

    protected String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
