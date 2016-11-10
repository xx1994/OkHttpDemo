package com.xx.myokhttpdemo.utils;


import okhttp3.Response;

public interface DownloadProgressListener {
    /**
     * @param bytesRead     已下载字节数
     * @param contentLength 总字节数
     * @param done          是否下载完成
     */
    void onDownloadProgress(long bytesRead, long contentLength, boolean done);

    /**
     * 下载成功回调
     *
     * @param response
     */
    void onDownloadSuccess(Response response);

    /**
     * 下载失败回调
     *
     * @param e
     */
    void onDownloadFailed(Exception e);
}