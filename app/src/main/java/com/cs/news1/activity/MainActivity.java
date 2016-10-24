package com.cs.news1.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.cs.news1.R;
import com.cs.news1.adpter.MyAdapter;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.fragment.TabCook;
import com.cs.news1.fragment.TabJoke;
import com.cs.news1.fragment.TabNews;
import com.cs.news1.fragment.TabPhoto;
import com.cs.news1.fragment.TabRxjava;
import com.cs.news1.fragment.TabVideo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout tab_main;
    private ViewPager vp_main;
    private List<BaseFragment> mList_fm;
    private List<String> mList_title;
    private MyAdapter mAdapter;
    private Toolbar mToolBar;
    private ActionBarDrawerToggle mDrawerToggle;//这个是箭头
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);


        initView();
    }

    private void showBottomSheetDialog() {
        final BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
        View sheetDialogView = getLayoutInflater().inflate(R.layout.sheet_dialog, null);
        sheetDialogView.findViewById(R.id.dialog_item1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog ad=new AlertDialog.Builder(MainActivity.this).create();
                ad.setTitle("AlertDialog");
                ad.setIcon(R.mipmap.icon_touxiang);
                ad.setMessage("我是消息内容");
                ad.setButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mBottomSheetDialog.dismiss();

                    }
                });
                ad.setButton2("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                ad.show();


            }
        }); sheetDialogView.findViewById(R.id.dialog_item2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "你点击了2", Toast.LENGTH_SHORT).show();
            }
        }); sheetDialogView.findViewById(R.id.dialog_item3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "你点击了3", Toast.LENGTH_SHORT).show();

            }
        });

        mBottomSheetDialog.setContentView(sheetDialogView);

        mBottomSheetDialog.show();
    }

    private void initView() {
        mToolBar= (Toolbar) findViewById(R.id.tl_main);
        mNavigationView = (NavigationView) findViewById(R.id.nv_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main);

        mToolBar.setTitle("标题");
        mToolBar.inflateMenu(R.menu.menu_main);
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_bottosheetDialog:
                        showBottomSheetDialog();
                        break;
                    case R.id.action_about:
                        Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com/"));
                        startActivity(intent);
                        break;


                }

                return true;
            }
        });
        // getSupportActionBar().setHomeButtonEnabled(true);  //设置返回键可用
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true); //创建返回键，并实现打开关/闭监听
        /*
        * 给定的Activity将被链接到指定的DrawerLayout及其ActionBar的向上按钮将被设置为自定义绘制。
        此drawable在抽屉关闭时显示一个汉堡图标，当抽屉打开时显示一个箭头。 它在抽屉打开时在这两个状态之间动画。
        * */
        /*ActionBarDrawerToggle ( Activity activity, DrawerLayout drawerLayout, Toolbar toolbar, int openDrawerContentDescRes, int closeDrawerContentDescRes)*/
        mDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,mToolBar,R.string.app_name,R.string.app_name){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // mToolBar.setVisibility(View.GONE);
                mToolBar.setTitle("");
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // mToolBar.setVisibility(View.VISIBLE);
                mToolBar.setTitle("标题");
            }
        };
        mDrawerToggle.syncState();  //初始化状态
        mDrawerLayout.addDrawerListener(mDrawerToggle); //将DrawerLayout与DrawerToggle绑定

        View view=mNavigationView.inflateHeaderView(R.layout.draw_header);
        TextView userName= (TextView) view.findViewById(R.id.tv_header_content);
        userName.setText("安卓开发高手联盟");
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_item1:
                        Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation_item2:
                        Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation_item3:
                        Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation_sub_item1:
                        Toast.makeText(MainActivity.this, "4", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation_sub_item2:
                        Toast.makeText(MainActivity.this, "5", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.navigation_sub_item3:
                        Toast.makeText(MainActivity.this, "6", Toast.LENGTH_SHORT).show();
                        return true;


                }

                return true;
            }
        });


        tab_main = (TabLayout) findViewById(R.id.tab_main);
        vp_main = (ViewPager) findViewById(R.id.vp_main);
       // tab_main.setTabMode(TabLayout.MODE_SCROLLABLE);

        mList_title=new ArrayList<>();
        mList_fm=new ArrayList<>();
        mList_fm.add(new TabNews());
        mList_fm.add(new TabJoke());//将fragment添加到fragmentList的list容器里
        mList_fm.add(new TabPhoto());
        mList_fm.add(new TabVideo());
        mList_fm.add(new TabCook());
        mList_fm.add(new TabRxjava());


        mList_title.add("新闻");
        mList_title.add("笑话");
        mList_title.add("图片");
        mList_title.add("视频");
        mList_title.add("食物");
        mList_title.add("Rxjava");

        mAdapter=new MyAdapter(getSupportFragmentManager(),mList_fm,mList_title);
        vp_main.setAdapter(mAdapter);
        tab_main.setupWithViewPager(vp_main);
        tab_main.setTabsFromPagerAdapter(mAdapter);//虽然过时了但是不能去掉，去掉后
        //如果是滑动操作的话没事，但是使用标签点击的时候就不行了。


    }
}
