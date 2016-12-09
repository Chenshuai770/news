package com.cs.news1.activity.adpter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.cs.news1.R;
import com.cs.news1.activity.MainActivity;

/**
 * Created by chenshuai on 2016/12/8.
 */

public class WelComeActivity extends Activity {
    private ImageView iv_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_welcome);
        initView();
    }

    private void initView() {
        iv_welcome = (ImageView) findViewById(R.id.iv_welcome);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv_welcome.setImageResource(R.mipmap.a);
                startActivity(new Intent(WelComeActivity.this, MainActivity.class));
                finish();
            }
        }, 2000);
      /*  Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map(new Func1<Long, Object>() {
                    @Override
                    public Object call(Long aLong) {
                        iv_welcome.setImageResource(R.mipmap.a);
                        return null;
                    }
                }).subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                startActivity(new Intent(WelComeActivity.this, MainActivity.class));
                finish();

            }
        });*/
    }
}
