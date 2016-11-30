package com.cs.news1.fragment.jokeAdpter;

import com.cs.news1.entry.Jokes;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 *
 * Created by chenshuai on 2016/11/29.
 */

public interface MyJokes {
    @GET("joke/content/list.from")
    Observable<Jokes>getData(@QueryMap Map<String,String > options);
}
