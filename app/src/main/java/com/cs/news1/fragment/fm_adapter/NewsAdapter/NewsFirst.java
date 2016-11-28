package com.cs.news1.fragment.fm_adapter.NewsAdapter;

import com.cs.news1.entry.Keshi;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by chenshuai on 2016/11/28.
 */

public interface NewsFirst {
    @GET("/api/department/all")
    Observable<Keshi> getData();

}
