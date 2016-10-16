package com.cs.news1.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.cs.news1.R;
import com.cs.news1.adpter.MyAdapter;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.fragment.TabJoke;
import com.cs.news1.fragment.TabNews;
import com.cs.news1.fragment.TabPhoto;
import com.cs.news1.fragment.TabVideo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab_main;
    private ViewPager vp_main;
    private List<BaseFragment> mList_fm;
    private List<String> mList_title;
    private MyAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
    }

    private void initView() {
        tab_main = (TabLayout) findViewById(R.id.tab_main);
        vp_main = (ViewPager) findViewById(R.id.vp_main);
       // tab_main.setTabMode(TabLayout.MODE_SCROLLABLE);

        mList_title=new ArrayList<>();
        mList_fm=new ArrayList<>();
        mList_fm.add(new TabNews());
        mList_fm.add(new TabJoke());//将fragment添加到fragmentList的list容器里
        mList_fm.add(new TabPhoto());
        mList_fm.add(new TabVideo());


        mList_title.add("新闻");
        mList_title.add("笑话");
        mList_title.add("图片");
        mList_title.add("视频");

        mAdapter=new MyAdapter(getSupportFragmentManager(),mList_fm,mList_title);
        vp_main.setAdapter(mAdapter);
        tab_main.setupWithViewPager(vp_main);
        tab_main.setTabsFromPagerAdapter(mAdapter);//虽然过时了但是不能去掉，去掉后
        //如果是滑动操作的话没事，但是使用标签点击的时候就不行了。


    }
}
