package com.cs.news1.fragment.fm_adpters;

import com.cs.news1.entry.News;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by chenshuai on 2016/11/28.
 */

public interface MyNews {
    @GET("toutiao/index")
    Observable<News> getDates(@QueryMap Map<String ,String > options);
}
