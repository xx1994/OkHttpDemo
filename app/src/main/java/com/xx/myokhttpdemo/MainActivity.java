package com.xx.myokhttpdemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.DownloadListener;
import android.widget.Button;
import android.widget.TextView;

import com.xx.myokhttpdemo.utils.DownloadProgressListener;
import com.xx.myokhttpdemo.utils.OkHttpUtils;
import com.xx.myokhttpdemo.utils.RBResponse;
import com.xx.myokhttpdemo.utils.UploadProgressListener;
import com.xx.myokhttpdemo.utils.WebResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements WebResponse, UploadProgressListener, DownloadProgressListener {


    private TextView textView;
    private Button btn1;
    private static final String webUrl = "http://192.168.2.182/Test/servlet/JsonTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Map<String, String> map = new HashMap<String, String>();
        map.put("name", "zhansan");
        map.put("pas", "adasdas");
        map.put("age", "12");

        textView = (TextView) findViewById(R.id.textview);
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //不带参数请求网络
//                OkHttpUtils.getInstance().getString(MainActivity.this, webUrl, 1, TestBean.class);
                //带参数请求网络，传入Map<String,String>
                OkHttpUtils.getInstance().getStringWithParam(MainActivity.this, webUrl, 1, TestBean.class, map);
                //文件上传
              /*  String url = "http://192.168.4.113/Test/servlet/FormServlet";
                Map<String, String> fileMap = new HashMap<String, String>();
                fileMap.put("file.txt", Environment.getExternalStorageDirectory().getAbsolutePath());
                fileMap.put("test.txt", Environment.getExternalStorageDirectory().getAbsolutePath());
                OkHttpUtils.getInstance().fileUpload(MainActivity.this, url, fileMap, map);*/
                //文件下载
//                String url = "http://192.168.4.109/Test/1.png";
//                OkHttpUtils.getInstance().fileDownload(MainActivity.this, url, Environment.getExternalStorageDirectory().getAbsolutePath(), "1.png");
            }
        });
    }

    /**
     * @param call
     * @param resultBean  结果Bean
     * @param requestCode 请求鉴别唯一的自定义CODE
     * @throws IOException
     */
    @Override
    public void onSuccessResponse(Call call, RBResponse resultBean, int requestCode) throws IOException {
        /**转化为目标Bean*/
        TestBean bean = (TestBean) resultBean;

        String str = bean.getData().get(1).getCarId();
        Log.i("TAG", bean.getData().size() + "     " + str);
        textView.setText(str);
    }

    @Override
    public void onFailResponse(Call call, IOException e, int requestCode) {
        Log.i("TAG", "请求失败");
    }

    @Override
    public void onUploadSuccess(Response response) {
        Log.i("TAG", "上传成功");
        textView.setText("成功");
    }

    @Override
    public void onUploadFailed(Exception e) {
    }

    @Override
    public void onUploadProgress(long bytesWritten, long contentLength, boolean done, String fileName) {
        textView.setText(bytesWritten + "");
        Log.i("TAG", bytesWritten + "----" + contentLength + "----" + fileName);
    }


    @Override
    public void onDownloadProgress(long bytesRead, long contentLength, boolean done) {
        Log.i("TAG", bytesRead + "---" + contentLength + "----" + done);
    }

    @Override
    public void onDownloadSuccess(Response response) {
        textView.setText("下载成功");
    }

    @Override
    public void onDownloadFailed(Exception e) {
        textView.setText("下载失败");
    }
}
