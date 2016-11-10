package com.xx.myokhttpdemo.utils;


import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.xx.myokhttpdemo.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by XuXiang on 2016/11/8.
 */
public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils = null;
    private static Handler mainHandler;

    public static synchronized OkHttpUtils getInstance() {
        if (okHttpUtils == null) {
            okHttpUtils = new OkHttpUtils();
            //更新UI线程
            mainHandler = new Handler(Looper.getMainLooper());
        }
        return okHttpUtils;
    }

    /**
     * Gson解析
     *
     * @param jsonString 传入的json String字符串
     * @param beanObj    进行Gson解析的Bean
     * @return
     */
    private static RBResponse getJson(String jsonString, Class beanObj) {
        Gson gson = new Gson();
        RBResponse resultBean = (RBResponse) gson.fromJson(jsonString, beanObj);
        return resultBean;
    }

    /**
     * 不带参数的请求网络
     *
     * @param mResponse   实现网络请求接口，调用此工具类进行网络访问的主类
     * @param url         网络请求url
     * @param requestCode 鉴别请求的请求码（区分同一个类中多次网络请求）
     * @param jsonBean    解析json数据的Bean
     */
    public void getString(final WebResponse mResponse, String url, final int requestCode, final Class jsonBean) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder()
                .build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                mResponse.onFailResponse(call, e, requestCode);
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final RBResponse result = getJson(response.body().string(), jsonBean);
                //UI线程 回调出去可以直接更新UI
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mResponse.onSuccessResponse(call, result, requestCode);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /**
     * 带参数的请求网络,传入Map<String,String>
     *
     * @param mResponse   实现网络请求接口，调用此工具类进行网络访问的主类
     * @param url         网络请求url
     * @param requestCode 鉴别请求的请求码（区分同一个类中多次网络请求）
     * @param jsonBean    解析json数据的Bean
     * @param map         带参数请求网络的参数 Map<String,String>
     */
    public void getStringWithParam(final WebResponse mResponse, String url, final int requestCode, final Class jsonBean, Map<String, String> map) {
        OkHttpClient mOkHttpClient = new OkHttpClient();
        FormBody.Builder builder = new FormBody.Builder();
        Iterator i = map.keySet().iterator();
        while (i.hasNext()) {
            String key = i.next().toString();
            builder.add(key, map.get(key));
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mResponse.onFailResponse(call, e, requestCode);
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final RBResponse result = getJson(response.body().string(), jsonBean);
                //UI线程 回调出去可以直接更新UI
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mResponse.onSuccessResponse(call, result, requestCode);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /**
     * 文件上传，支持多文件上传，并且可带参数上传
     *
     * @param uploadProgressListener 调用并且实现了上传接口的主类
     * @param webUrl                 上传的服务器接口url
     * @param fileMap                文件Map集合 键为文件名，值为文件路径，防止相同路径map集合会覆盖
     * @param paramMap               添加的参数Map集合 没有置null
     */
    public void fileUpload(UploadProgressListener uploadProgressListener, String webUrl, Map<String, String> fileMap, Map<String, String> paramMap) {
        // 多个文件集合
        List<File> list = new ArrayList<File>();
        //迭代添加文件
        Iterator fileIt = fileMap.keySet().iterator();
        while (fileIt.hasNext()) {
            String fileName = fileIt.next().toString();
            File file_upload = new File(fileMap.get(fileName), fileName);
            list.add(file_upload);
        }
        OkHttpClient mOkHttpClient = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder();
        // 设置为表单类型
        builder.setType(MultipartBody.FORM);
        //迭代添加请求参数
        if (paramMap != null) {
            Iterator paramIt = paramMap.keySet().iterator();
            while (paramIt.hasNext()) {
                String paramKey = paramIt.next().toString();
                // 添加表单键值
                builder.addFormDataPart(paramKey, paramMap.get(paramKey));
            }
        }
        for (File file : list) {
            // 添加多个文件
            RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            RequestBody Body = new ProgressRequestBody(fileBody, uploadProgressListener, file.getName());
            builder.addFormDataPart("files", file.getName(), Body);
        }
        Request request = new Request.Builder().url(webUrl).post(builder.build()).build();
        mOkHttpClient.newCall(request).enqueue(new MyUploadCallBack(uploadProgressListener));
    }

    private class MyUploadCallBack implements Callback {
        private UploadProgressListener progressListener;

        public MyUploadCallBack(UploadProgressListener progressListener) {
            this.progressListener = progressListener;
        }

        @Override
        public void onFailure(Call arg0, final IOException e) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressListener.onUploadFailed(e);
                }
            });
        }

        @Override
        public void onResponse(Call arg0, final Response response) throws IOException {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    progressListener.onUploadSuccess(response);
                }
            });
        }
    }


    /**
     * 文件下载 支持UI线程下载进度回调
     *
     * @param downloadProgressListener 调用并且实现了上传接口的主类
     * @param webUrl                   下载的接口
     * @param fileSavePath             文件保存的路径
     * @param fileSaveName             文件保存的名称
     */
    public void fileDownload(final DownloadProgressListener downloadProgressListener, String webUrl, final String fileSavePath, final String fileSaveName) {
        //增加拦截器 然后利用自定义ProgressResponseBody去获取下载进度
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder().body(new ProgressResponseBody(originalResponse.body(), downloadProgressListener)).build();
            }
        }).build();
        Request request = new Request.Builder().url(webUrl).build();
        mOkHttpClient.newCall(request).enqueue(new MyDownloadCallBack(downloadProgressListener, fileSavePath, fileSaveName));
    }

    private class MyDownloadCallBack implements Callback {
        private DownloadProgressListener downloadProgressListener;
        private String fileSaveName;
        private String fileSavePath;

        public MyDownloadCallBack(DownloadProgressListener downloadProgressListener, String fileSavePath, String fileSaveName) {
            this.downloadProgressListener = downloadProgressListener;
            this.fileSaveName = fileSaveName;
            this.fileSavePath = fileSavePath;
        }

        //下载失败回调
        @Override
        public void onFailure(Call call, final IOException e) {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    downloadProgressListener.onDownloadFailed(e);
                }
            });
        }

        //下载回调
        @Override
        public void onResponse(Call call, final Response response) throws IOException {
            // 将返回结果转化为流，并写入文件
            int len;
            byte[] buf = new byte[2048];
            InputStream inputStream = response.body().byteStream();
            // 可以在这里自定义路径
            File file1 = new File(fileSavePath, fileSaveName);
            FileOutputStream fileOutputStream = new FileOutputStream(file1);

            while ((len = inputStream.read(buf)) != -1) {
                fileOutputStream.write(buf, 0, len);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            inputStream.close();
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    downloadProgressListener.onDownloadSuccess(response);
                }
            });
        }
    }
}

