package com.cs.news1.fragment.fm_adapter.WebAdapter;

import com.cs.news1.entry.JuheNews;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chenshuai on 2016/10/31.
 * 里面的数据是list之前的数据
 */

public interface WebAPi {

    @GET("/toutiao/index")
    Call<JuheNews.ResultBean> getDage(@Query("top")String top,@Query("key") String key);
}

