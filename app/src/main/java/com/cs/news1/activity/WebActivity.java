package com.cs.news1.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.news1.R;
import com.cs.news1.entry.News;

import java.util.ArrayList;
import java.util.List;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * Created by chenshuai on 2016/11/29.
 */

public class WebActivity extends Activity {
    private ImageView mIvWebReturn;
    private TextView mWebTitle;
    private ImageView mIvWebShare;
    private WebView mWebAct;
    private WebActivity mActivity;
    private List<News.ResultBean.DataBean> mList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web);
        initView();
        inttData();


    }

    private void inttData() {
    }

    private void initView() {
        mIvWebReturn = (ImageView) findViewById(R.id.iv_web_return);
        mWebTitle= (TextView) findViewById(R.id.tv_web_title);
        mWebTitle.setVisibility(View.GONE);
        mIvWebShare = (ImageView) findViewById(R.id.iv_web_share);
        mWebAct = (WebView) findViewById(R.id.web_act);
        Intent intent = getIntent();
        mList= intent.getParcelableArrayListExtra("myNewsUrl");

        int pos = (int) intent.getExtras().get("pos");
        String url = mList.get(pos).getUrl();
        mWebTitle.setText(mList.get(pos).getRealtype());
        mWebAct.loadUrl(url);
        mWebAct.setTag("first");
        // TODO: 2016/11/29 如果访问的页面中有 Javascript,则 WebView 必须设置支持 Javascript
        mWebAct.getSettings().setJavaScriptEnabled(true);
        //支持缩放
        mWebAct.getSettings().setSupportZoom(false);
        mWebAct.getSettings().setBuiltInZoomControls(false);
        // TODO: 2016/11/29 .如果页面中链接,如果希望点击链接继续在当前browser中响应,而不是新开Android的系统browser中响应该链接,必须覆盖 WebView的WebViewClient对象.
        mWebAct.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mIvWebReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mWebAct != null && mWebAct.canGoBack()) {
                    mWebAct.goBack();
                } else {
                    finish();
                }
            }
        });

    }

    // TODO: 2016/11/29 4.如果不做任何处理 ,浏览网页,点击系统“Back”键,整个 Browser 会调用 finish()而结束自身,如果希望浏览的网页回退而不是推出浏览器,需要在当前Activity中处理并消费掉该 Back 事件.(代码有些精简)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KEYCODE_BACK) && mWebAct.canGoBack()) {
            mWebAct.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
