 OkHttpDemo
对OkHttp进行简单的封装，基本实现简单的网络post请求，进行Gson处理，直接返回Bean。文件上传支持多文件和参数一起提交，还有文件下载，都支持进度在UI线程返回

使用方法:
添加 gson okhttp okio 三个jar包

网络psot请求

   //不带参数请求网络
	 
         OkHttpUtils.getInstance().getString(MainActivity.this, webUrl, 1, TestBean.class);
				 
      说明：
       MainActivity实现WebResponse接口 接口回调onSuccessResponse（）成功    onFailResponse（）失败  返回Bean对象。
       TestBean.class Gson解析调用的Bean文件   继承RBResponse类即可。
       增加int型requestCode，便于在回调是分辨不同请求。
			 
   //带参数请求网络，传入Map<String,String>
	 
         OkHttpUtils.getInstance().getStringWithParam(MainActivity.this, webUrl, 1, TestBean.class, map);
				 
文件上传

     OkHttpUtils.getInstance().fileUpload(MainActivity.this, url, fileMap, map);
   说明：
    fileMap为文件map集合，后面的是参数集合，如果为空置为null
		
		
文件下载

     OkHttpUtils.getInstance().fileDownload(MainActivity.this, url, Environment.getExternalStorageDirectory().getAbsolutePath(), "1.png");
    说明:
      四个参数：1、实现DownloadProgressListener的主类   2、文件接口url  3、文件保存路径  4、文件保存名
       
