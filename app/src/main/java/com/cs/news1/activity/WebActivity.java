package com.cs.news1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        final String url = bundle.getString("url");
        act_web.loadUrl(url);
        act_web.getSettings().setJavaScriptEnabled(true);
        act_web.setWebViewClient(new WebViewClient() {
            //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String data) {
                view.loadUrl(data);
                return true;
            }


        });
      /*  act_web.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.KEYCODE_BACK) && act_web.canGoBack()) {
                    act_web.goBack(); // goBack()表示返回WebView的上一页面
                    return true;
                }
                return false;
            }
        });
*/

    }

    private void initView() {
        act_web = (WebView) findViewById(R.id.act_web);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
