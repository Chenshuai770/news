package com.cs.news1.fragment.photosAdapter;

import com.cs.news1.entry.Photos;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by chenshuai on 2016/11/30.
 */

public interface MyPhotos {
    //http://gank.io/api/data/福利/10/1
    @GET("api/data/福利/{count}/{page}")
    Observable<Photos>getDatas(@Path("count") int count,@Path("page") int page);
}
