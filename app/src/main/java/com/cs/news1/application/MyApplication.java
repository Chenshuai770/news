package com.cs.news1.application;

import android.app.Application;
import android.util.Log;

import com.cs.news1.utils.OkNetUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by chenshuai on 2016/11/28.
 */

/**
 * Created by chenshuai on 2016/11/4.
 * client.interceptors().add(new LoggingInterceptor()); //添加应用拦截器
 *client.networkInterceptors().add(new LoggingInterceptor()); //添加网络拦截器

 */

public class MyApplication extends Application{
    private static final String TAG = "maning----";
    private static MyApplication application;
    @Override
    public void onCreate() {
        super.onCreate();
        application=this;

    }
    public static MyApplication getInstance(){
        return application;
    }

    /**
     * Application.getCacheDir()方法用于获取/data/data/<application package>/cache目录
     * Application.getFilesDir()方法用于获取/data/data/<application package>/files目录
     * Context.getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
     *Context.getExternalCacheDir()方法可以获取到 SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
     * @return
     */
    public static OkHttpClient defalutOkHttpClient(){
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.writeTimeout(30 * 1000, java.util.concurrent.TimeUnit.MILLISECONDS);//写入超时为30
        client.readTimeout(20 * 1000, java.util.concurrent.TimeUnit.MILLISECONDS);//读出超时为20
        // client.retryOnConnectionFailure(true);//重新连接
        client.connectTimeout(15 * 1000, java.util.concurrent.TimeUnit.MILLISECONDS);//网络延时为15
        //设置缓存路径
        File myCache = new File(application.getCacheDir(), "myCache");
        //设置缓存 10M
        Cache cache = new Cache(myCache, 10 * 1024 * 1024);
        client.cache(cache);
        //设置拦截器
        client.addInterceptor(LoggingInterceptor);
        client.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
        client.addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);

        return client.build();


    }
    //应用 Interceptor读取缓存
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR=new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //方案一：有网和没有网都是先读缓存
            /**
             * Interceptor 接口只包含一个方法 intercept，其参数是 Chain 对象
             * Chain 对象表示的是当前的拦截器链条。
             * 通过 Chain 的 request 方法可以获取到当前的 Request 对象。
             * 在使用完 Request 对象之后，通过 Chain 对象的 proceed 方法来继续拦截器链条的执行。
             * 当执行完成之后，可以对得到的 Response 对象进行额外的处理。

             */

          /*  Request request = chain.request();
             Log.d(TAG, "request:" + request);
            Response response = chain.proceed(request);
            Log.d(TAG, "response:" + response);

            String cacheCotrol = request.cacheControl().toString();
            if (TextUtils.isEmpty(cacheCotrol)) {
                cacheCotrol="public, max-age=60";

            }

            return response.newBuilder()
                    .header("Cache-Control",cacheCotrol)
                    .removeHeader("Pragma")
                    .build();*/
            //方案二：无网读缓存，有网根据过期时间重新请求
            boolean conection = OkNetUtils.hasNetWorkConection(MyApplication.getInstance());
            Request request = chain.request();
            //如果没有网路的话
            if (!conection) {
                //  CacheControl.FORCE_CACHE; //仅仅使用缓存
                request=request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (conection) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                Log.i(TAG, "cacheControl:" + cacheControl);
                response.newBuilder()
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .header("Cache-Control", cacheControl)
                        .build();
            }else {//maxStale 请求:意思是,我允许缓存者，发送一个,过期不超过指定秒数的,陈旧的缓存.响应:同上.
                // .only-if-cached:(仅为请求标头)请求:告知缓存者,我希望内容来自缓存，我并不关心被缓存响应,是否是新鲜的.
                int maxStale = 60 * 60 * 24 * 7;//7天缓存
                response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();

            }
            return response;
        }
    };
    //查询url 响应体的代码
    public static final Interceptor LoggingInterceptor= new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            Log.i(TAG, String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            Log.i(TAG, String.format("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;

        }
    };

}

