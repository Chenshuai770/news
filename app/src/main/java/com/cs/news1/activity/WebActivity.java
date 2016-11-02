package com.cs.news1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import com.cs.news1.R;

/**
 * Created by chenshuai on 2016/10/31.
 */

public class WebActivity extends Activity {
    private WebView act_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initView();
    }

    private void initView() {
        act_web = (WebView) findViewById(R.id.act_web);
    }
}
