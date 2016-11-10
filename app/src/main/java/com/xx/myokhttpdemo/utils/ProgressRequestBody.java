package com.xx.myokhttpdemo.utils;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * 包装的请求体，处理进度
 */
public class ProgressRequestBody extends RequestBody {
    // 实际的待包装请求体
    private final RequestBody requestBody;
    // 进度回调接口
    private final UploadProgressListener progressListener;
    // 包装完成的BufferedSink
    private BufferedSink bufferedSink;
    //正在上传的文件名
    private String fileName;

    /**
     * 构造函数，赋值
     *
     * @param requestBody      待包装的请求体
     * @param progressListener 回调接口
     * @param fileName         返回正在上传文件的文件名
     */
    public ProgressRequestBody(RequestBody requestBody, UploadProgressListener progressListener, String fileName) {
        this.requestBody = requestBody;
        this.progressListener = progressListener;
        this.fileName = fileName;
    }

    /**
     * 重写调用实际的响应体的contentType
     *
     * @return MediaType
     */
    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    /**
     * 重写调用实际的响应体的contentLength
     *
     * @return contentLength
     * @throws IOException 异常
     */
    @Override
    public long contentLength() throws IOException {
        return requestBody.contentLength();
    }

    /**
     * 重写进行写入
     *
     * @param sink BufferedSink
     * @throws IOException 异常
     */
    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        if (bufferedSink == null) {
            // 包装
            bufferedSink = Okio.buffer(sink(sink));
        }
        // 写入
        requestBody.writeTo(bufferedSink);
        // 必须调用flush，否则最后一部分数据可能不会被写入
        bufferedSink.flush();

    }

    /**
     * 写入，回调进度接口
     *
     * @param sink Sink
     * @return Sink
     */
    private Sink sink(Sink sink) {
        return new ForwardingSink(sink) {
            // 当前写入字节数
            long bytesWritten = 0L;
            // 总字节长度，避免多次调用contentLength()方法
            long contentLength = 0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength == 0) {
                    // 获得contentLength的值，后续不再调用
                    contentLength = contentLength();
                }
                // 增加当前写入的字节数
                bytesWritten += byteCount;
                // 进入UI线程回调
                Handler mainHandler = new Handler(Looper.getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressListener.onUploadProgress(bytesWritten, contentLength, bytesWritten == contentLength, fileName);
                    }
                });
            }
        };
    }
}
