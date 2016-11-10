package com.xx.myokhttpdemo.utils;

import okhttp3.Response;

/**
 * 上传文件的回调接口
 */
public interface UploadProgressListener {

    /**
     * 上传成功
     *
     * @param response
     */
    void onUploadSuccess(Response response);

    /**
     * 上传失败
     *
     * @param e
     */
    void onUploadFailed(Exception e);

    /**
     * 上传时进度
     *
     * @param bytesWritten  当前进度
     * @param contentLength 总大小
     * @param done          是否上传完成
     * @param fileName      上传文件的文件名
     */
    void onUploadProgress(long bytesWritten, long contentLength, boolean done, String fileName);
}
