package com.xx.myokhttpdemo.utils;

import android.os.Handler;

import java.io.IOException;

import okhttp3.Call;

/**
 * Created by XuXiang on 2016/11/8.
 * 网络请求 请求结果返回Bean接口
 */
public interface WebResponse {
    /**
     * 成功结果返回
     *
     * @param call
     * @param resultBean  结果Bean
     * @param requestCode 请求鉴别唯一的自定义CODE
     * @throws IOException
     */
    public void onSuccessResponse(Call call, RBResponse resultBean, int requestCode) throws IOException;

    /**
     * 失败结果返回
     *
     * @param call
     * @param e
     */
    public void onFailResponse(Call call, IOException e, int requestCode);
}
