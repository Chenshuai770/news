package com.cs.news1.fragment.fm_adapter.CookAdapter;

import com.cs.news1.entry.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chenshuai on 2016/10/24.
 * page	否	int	请求页数，默认page=1
 *rows	否	int	每页返回的条数，默认rows=20
 *id	否	int	分类ID，默认返回的是全部。这里的ID就是指分类的ID
 */

public interface ApiCook {
    @GET("/api/top/list")
    Call<News> getDate(@Query("page") int page, @Query("rows") int rows,@Query("id") int id);
}
