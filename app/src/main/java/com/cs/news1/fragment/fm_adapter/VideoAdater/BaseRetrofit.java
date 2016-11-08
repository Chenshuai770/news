package com.cs.news1.fragment.fm_adapter.VideoAdater;

import com.cs.news1.MyApplacation.MyApplication;



import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenshuai on 2016/11/7.
 */

public class BaseRetrofit {
    private static Retrofit retrofit;
    //接口请求的Url
    public static final String BASEURL="http://v.juhe.cn/";
    public static MnAPIService getMnAPIService(){
        if (retrofit == null) {
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(MyApplication.defalutOkHttpClient())
                    .build();
        }
        return retrofit.create(MnAPIService.class);
    }

}
