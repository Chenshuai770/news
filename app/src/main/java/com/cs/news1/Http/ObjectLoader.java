package com.cs.news1.Http;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenshuai on 2016/12/20.
 * /
 *
 * 将一些重复的操作提出来，放到父类以免Loader 里每个接口都有重复代码
 * Created by zhouwei on 16/11/10.
 *
 */


public  class ObjectLoader {
    protected  <T> Observable<T> ob(Observable<T> observable){
        return observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
