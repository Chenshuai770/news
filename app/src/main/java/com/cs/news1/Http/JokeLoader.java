package com.cs.news1.Http;

import com.cs.news1.entry.Jokes;
import com.cs.news1.fragment.jokeAdpter.MyJokes;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by chenshuai on 2016/12/20.
 */

public class JokeLoader  {

    private final MyJokes myJokes;
    private Map<String, String> options=new HashMap<>();

    public JokeLoader(Map<String ,String> map) {
        this.options=map;
        myJokes = RetrofitServiceManager.getInstance().create(MyJokes.class);
    }
    public  Observable<Jokes> observable(){
        Observable<Jokes> observable = myJokes.getData(options);
        Observable<Jokes> jokesObservable = observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return jokesObservable;
    }
}
