package com.cs.news1.fragment.videoAdapter;

import com.cs.news1.entry.Videos;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by chenshuai on 2017/1/23.
 */

public interface MyVideos {
    /**
     * 视频 http://c.3g.163.com/nc/video/list/V9LG4CHOR/n/10-10.html
     * page必须大于1
     */

    @GET("nc/video/list/V9LG4CHOR/n/10-10.html")
    Observable<Videos> getdata();
}
