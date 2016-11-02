package com.cs.news1.fragment.fm_adapter.JokeAdater;

import com.cs.news1.entry.Recreation;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by chenshuai on 2016/10/26.
 * page	否	int	请求页数，默认page=1
 * rows	否	int	每页返回的条数，默认rows=20
 *id	否	int	分类ID，默认返回的是全部。这里的ID就是指分类的ID
 */

public interface ApiJoke{
    @GET("/api/top/list")
    Observable<Recreation> getData(@Query("page") int page, @Query("rows") int rows, @Query("id") int id);

}
