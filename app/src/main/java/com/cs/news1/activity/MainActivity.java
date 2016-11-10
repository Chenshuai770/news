package com.cs.news1.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cs.news1.R;
import com.cs.news1.adpter.MyAdapter;
import com.cs.news1.base.BaseFragment;
import com.cs.news1.fragment.TabCook;
import com.cs.news1.fragment.TabJoke;
import com.cs.news1.fragment.TabNews;
import com.cs.news1.fragment.TabPhoto;
import com.cs.news1.fragment.TabRxjava;
import com.cs.news1.fragment.TabVideo;
import com.cs.news1.fragment.TabWebview;
import com.cs.news1.utils.GlideCircleTransform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tab_main;
    private ViewPager vp_main;
    private List<BaseFragment> mList_fm;
    private List<String> mList_title;
    private MyAdapter mAdapter;
    private Toolbar mToolBar;
    private ActionBarDrawerToggle mDrawerToggle;//这个是箭头
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;

    private static final String CAMERA_DIR = "/dcim/";
    private static final String albumName = "CameraSample";
    private String mFilePath;
    private ImageView headerphoto;
    private TextView userName;

    private RadioButton rb_main_btn1;
    private RadioButton rb_main_btn2;
    private RadioButton rb_main_btn3;
    private RadioGroup rg_main_bottom;
    private RelativeLayout rl_main_bottom;


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
                AlertDialog ad = new AlertDialog.Builder(MainActivity.this).create();
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
        });
        sheetDialogView.findViewById(R.id.dialog_item2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "你点击了2", Toast.LENGTH_SHORT).show();
            }
        });
        sheetDialogView.findViewById(R.id.dialog_item3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "你点击了3", Toast.LENGTH_SHORT).show();

            }
        });

        mBottomSheetDialog.setContentView(sheetDialogView);

        mBottomSheetDialog.show();
    }

    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.tl_main);
        mNavigationView = (NavigationView) findViewById(R.id.nv_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_main);

        mToolBar.setTitle("标题");
        mToolBar.inflateMenu(R.menu.menu_main);
        mToolBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_bottosheetDialog:
                        showBottomSheetDialog();
                        break;
                    case R.id.action_about:
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.baidu.com/"));
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
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolBar, R.string.app_name, R.string.app_name) {
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

        View view = mNavigationView.inflateHeaderView(R.layout.draw_header);
        headerphoto = (ImageView) view.findViewById(R.id.iv_header_photo);
        Glide.with(this).load(R.mipmap.icon_touxiang).transform(new GlideCircleTransform(MainActivity.this))
                .override(100, 100)
                .crossFade()
                .into(headerphoto);
        headerphoto.setOnClickListener(this);
        userName = (TextView) view.findViewById(R.id.tv_header_content);
        userName.setText("安卓开发高手联盟");
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_item1:
                        Glide.with(MainActivity.this).load(R.mipmap.icon_touxiang).transform(new GlideCircleTransform(MainActivity.this))
                                .override(100, 100)
                                .crossFade()
                                .into(headerphoto);
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

        mList_title = new ArrayList<>();
        mList_fm = new ArrayList<>();
        mList_fm.add(new TabNews());
        mList_fm.add(new TabJoke());//将fragment添加到fragmentList的list容器里
        mList_fm.add(new TabPhoto());
        mList_fm.add(new TabVideo());
        mList_fm.add(new TabCook());
        mList_fm.add(new TabRxjava());
        mList_fm.add(new TabWebview());


        mList_title.add("新闻");
        mList_title.add("笑话");
        mList_title.add("图片");
        mList_title.add("视频");
        mList_title.add("食物");
        mList_title.add("Rxjava");
        mList_title.add("webtest");

        mAdapter = new MyAdapter(getSupportFragmentManager(), mList_fm, mList_title);
        vp_main.setAdapter(mAdapter);
        tab_main.setupWithViewPager(vp_main);
        tab_main.setTabsFromPagerAdapter(mAdapter);//虽然过时了但是不能去掉，去掉后
        //如果是滑动操作的话没事，但是使用标签点击的时候就不行了。

        mFilePath = Environment.getExternalStorageDirectory().getPath();
        mFilePath = mFilePath + "/" + "temp.jpeg";





        rb_main_btn1 = (RadioButton) findViewById(R.id.rb_main_btn1);
        rb_main_btn1.setOnClickListener(this);
        rb_main_btn2 = (RadioButton) findViewById(R.id.rb_main_btn2);
        rb_main_btn2.setOnClickListener(this);
        rb_main_btn3 = (RadioButton) findViewById(R.id.rb_main_btn3);
        rb_main_btn3.setOnClickListener(this);
        rg_main_bottom = (RadioGroup) findViewById(R.id.rg_main_bottom);
        rg_main_bottom.setOnClickListener(this);
        rl_main_bottom = (RelativeLayout) findViewById(R.id.rl_main_bottom);
        rl_main_bottom.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_header_photo:
                //设置contentView
                View contentview = LayoutInflater.from(MainActivity.this).inflate(R.layout.popwidow, null);
                final PopupWindow popupWindow = new PopupWindow(MainActivity.this);
                popupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
                popupWindow.setHeight(200);
                popupWindow.setFocusable(true);
                popupWindow.setContentView(contentview);
                //设置各个控件的点击响应
                TextView cammer = (TextView) contentview.findViewById(R.id.tv_canmer);
                cammer.setText(getResources().getString(R.string.canmer));
                final TextView photo = (TextView) contentview.findViewById(R.id.tv_photo);
                photo.setText(getResources().getString(R.string.photo));
                cammer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //  Toast.makeText(MainActivity.this, "你点击canmer", Toast.LENGTH_SHORT).show();

                        ImageFromCamera();

                        popupWindow.dismiss();
                    }
                });
                photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Toast.makeText(MainActivity.this, "你点击photo", Toast.LENGTH_SHORT);
                        ImageFromPhoto();
                        popupWindow.dismiss();
                    }
                });
                //显示PopupWindow
                View rootview = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_main, null);
                popupWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);

                break;
            case R.id.rb_main_btn1:
                vp_main.setTag(true);
                vp_main.setCurrentItem(0);
                break;


            case R.id.rb_main_btn2:
                vp_main.setTag(true);
                vp_main.setCurrentItem(2);
                break;

            case R.id.rb_main_btn3:
              Intent intent=new Intent(MainActivity.this,Personal.class);
                startActivity(intent);
                break;


        }

    }

    private void ImageFromPhoto() {
        //ACTION_GET_CONTENT 可以自定义内容选择选项
        Intent intet1 = new Intent(Intent.ACTION_GET_CONTENT);
        intet1.setType("image/*");
        startActivityForResult(intet1, 2);

    }

    private void ImageFromCamera() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//调用系统相机
      /*  //直接使用，没有缩小
        Uri photoUri = Uri.fromFile(new File(mFilePath));
        //可以对系统存储路径EXTRA_OUTPUT进行修改
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);*/
        startActivityForResult(intent, 1);  //用户点击了从相机获取

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {// 检测sd是否可用

                return;
            }
            Bundle bundle = data.getExtras();//获取二进制流;
            Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
            FileOutputStream b = null;
            File file = new File("/sdcard/myImage/");
            file.mkdirs();// 创建文件夹，名称为myimage

            //照片的命名，目标文件夹下，以当前时间数字串为名称，即可确保每张照片名称不相同。
            String str = null;
            Date date = null;
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");//获取当前时间，进一步转化为字符串
            date = new Date();
            str = format.format(date);
            String fileName = "/sdcard/myImage/" + str + ".jpg";

            try {
                b = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件.这里不写入数据会一直是原来的图片
                Glide.with(this)
                        .load(fileName)
                        .transform(new GlideCircleTransform(MainActivity.this))
                        .override(100, 100)
                        .crossFade()
                        .into(headerphoto);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else if (requestCode == 2) {
            if (data == null) {
                return;
            } else {
                Uri uri;
                uri = data.getData();
                Toast.makeText(this, uri.toString(), Toast.LENGTH_SHORT).show();
                Glide.with(this)
                        .load(uri)
                        .transform(new GlideCircleTransform(MainActivity.this))
                        .override(100, 100)
                        .crossFade()
                        .into(headerphoto);


                //converUri(uri);
            }
        }
    }


}
