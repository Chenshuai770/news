package com.cs.news1.fragment;

import android.content.Intent;
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
import com.cs.news1.activity.WebActivity;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.entry.JuheNews;
import com.cs.news1.fragment.fm_adapter.WebAdapter.WebAPi;
import com.cs.news1.fragment.fm_adapter.WebAdapter.WebAdater;
import com.cs.news1.views.MyDecoration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        initData();
        return view;
    }

    private void initData() {

        //新建缓存文件
        File cacheFile = new File(getContext().getCacheDir(), "[缓存目录]");
        int cacheSize=10*1024*1024;//10mib
        //指定文件大小
        Cache cache=new Cache(cacheFile,cacheSize);
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(cache)
                .build();


    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rl_webview);
        final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new MyDecoration(getContext(),MyDecoration.VERTICAL_LIST));

        adater=new WebAdater(getActivity(),list);
        recyclerView.setAdapter(adater);
        adater.setOnItemClickListern(new WebAdater.ItemClickListern() {
            @Override
            public void ItemClick(View view, int possiton) {
                //Toast.makeText(getActivity(), "你点击了"+possiton+"个", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(), WebActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("url",list.get(possiton).getUrl());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        String type="top";
        String key="ce18f1786cf2e609acbc076a4b6a2df5";
        String url="http://v.juhe.cn/toutiao/index";
       /* OkHttpUtils.get()
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
                });*/

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WebAPi webAPI = retrofit.create(WebAPi.class);
        List<String > list1=new ArrayList<>();
        list1.add("top");
        list1.add("shehui");
        list1.add("guonei");
        list1.add("yule");
        list1.add("tiyu");
        list1.add("junshi");
        list1.add("keji");
        list1.add("caijing");
        list1.add("shishang");
        Map<String ,String > options=new HashMap<>();
        options.put("type",list1.get(5));
        options.put("key",key);
        Call<JuheNews> call = webAPI.getDage(options);
        call.enqueue(new Callback<JuheNews>() {
          @Override
          public void onResponse(Call<JuheNews> call, Response<JuheNews> response) {
             Log.d("DDD", response.body().getReason()+"");
              list.addAll(response.body().getResult().getData());
              //list.addAll(response.body().getData());
              adater.notifyDataSetChanged();
          }
          @Override
          public void onFailure(Call<JuheNews> call, Throwable t) {
              t.printStackTrace();

          }
      });
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

