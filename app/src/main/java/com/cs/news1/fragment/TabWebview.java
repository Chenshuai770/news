package com.cs.news1.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.cs.news1.R;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.JuheNews;
import com.cs.news1.fragment.fm_adapter.WebAdapter.WebAdater;
import com.cs.news1.views.MyDecoration;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class TabWebview extends BaseFragment{


    private Button btn_web;
    private WebView webview;
    private static long page = 1;
    private RecyclerView recyclerView;
    private WebAdater adater;
    private List<JuheNews.ResultBean.DataBean> list=new ArrayList<>();
    private static final String KEY="appkey ce18f1786cf2e609acbc076a4b6a2df5";
    private static final String TYAP="top";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fm_webtest, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {



        recyclerView = (RecyclerView) view.findViewById(R.id.rl_webview);
        final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyDecoration(getContext(),MyDecoration.VERTICAL_LIST));
        adater=new WebAdater(getActivity(),list);
        recyclerView.setAdapter(adater);
        String type="top";
        String key="ce18f1786cf2e609acbc076a4b6a2df5";
        String url="http://v.juhe.cn/toutiao/index";
        OkHttpUtils.get()
                .url(url)
                .addParams("type",type)
                .addParams("key",key)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("TTT",response+"");
                        Gson gson=new Gson();
                        JuheNews juheNews = gson.fromJson(response, JuheNews.class);
                        if (juheNews.getError_code()==0) {
                            list.addAll(juheNews.getResult().getData());
                            adater.notifyDataSetChanged();
                        }

                    }
                });

      /*  Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebAPi webAPI = retrofit.create(WebAPi.class);
        Call<JuheNews.ResultBean> call = webAPI.getDage(TYAP,KEY);
        call.enqueue(new Callback<JuheNews.ResultBean>() {
          @Override
          public void onResponse(Call<JuheNews.ResultBean> call, Response<JuheNews.ResultBean> response) {
              Log.d("TTT", response.body().getData()+"");
              //list.addAll(response.body().getData());
              adater.notifyDataSetChanged();
          }

          @Override
          public void onFailure(Call<JuheNews.ResultBean> call, Throwable t) {
              t.printStackTrace();

          }
      });*/
    }


    /**
     * //创建retrofit的实例,传递参数
     * Retrofit retrofit = new Retrofit.Builder()
     * .baseUrl("http://www.tngou.net")
     * .addConverterFactory(GsonConverterFactory.create())
     * .build();
     * //retrofit与api接口关联,创建
     * ApiCook apiCook = retrofit.create(ApiCook.class);
     * //请求具体数据
     * Call<News> call = apiCook.getDate(1, 20, 1);
     * //异步任务提交数据
     * call.enqueue(new Callback<News>() {
     *
     * @param
     * @Override public void onResponse(Call<News> call, Response<News> response) {
     * mData.addAll(response.body().getTngou());
     * }
     * @Override public void onFailure(Call<News> call, Throwable t) {
     * Log.d(TAG, "onFailure: "+t.getMessage());
     * }
     * });
     */
   /* public interface WebAPI {
        @GET("/api/cook/show")
        Call<Webtest> getData(@Query("id") long id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_web:
                //创建retrofit的实例,传递参数
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://www.tngou.net")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                //retrofit与api接口关联,创建
                final WebAPI webapi = retrofit.create(WebAPI.class);
                Call<Webtest> call = webapi.getData(page);
                call.enqueue(new Callback<Webtest>() {
                    @Override
                    public void onResponse(Call<Webtest> call, Response<Webtest> response) {
                        String url = response.body().getUrl();
                        webview.loadUrl(url);
                        WebSettings settings = webview.getSettings();
                        settings.setJavaScriptEnabled(true);
                        page++;
                    }

                    @Override
                    public void onFailure(Call<Webtest> call, Throwable t) {
                        t.printStackTrace();

                    }
                });


                break;
        }*/
    }

