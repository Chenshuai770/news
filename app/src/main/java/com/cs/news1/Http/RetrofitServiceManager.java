package com.cs.news1.http;

import com.cs.news1.application.MyApplication;
import com.cs.news1.uri.Uri;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenshuai on 2016/12/20.
 */

public class RetrofitServiceManager {

    private final Retrofit retrofit;

    public RetrofitServiceManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Uri.JOKESURI)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(MyApplication.defalutOkHttpClient())
                .build();
    }

    private static class SingletonHolder{
        private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();
    }
    /**
     * 获取RetrofitServiceManager
     * @return
     */
    public static RetrofitServiceManager getInstance(){
        return SingletonHolder.INSTANCE;
    }
    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return retrofit.create(service);
    }
}
