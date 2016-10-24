package com.cs.news1.fragment.fm_adapter.JokeAdater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs.news1.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

/**
 * Created by chenshuai on 2016/10/16.
 */

public class JokeAdpter extends RecyclerView.Adapter {
    private final int TYPE_HEAD = 0;//表示首个位置的轮播图
    private final int TYPE_NORMAL = 1;//表示后面的内容
    private Context mContext;
    private List<String> banner_url;//轮播图片的位置，网路路径


    public JokeAdpter(Context mContext, List<String> banner_url) {
        this.mContext = mContext;
        this.banner_url=banner_url;
    }

    @Override//根据item创建视图
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder hodler = null;
        if (viewType == TYPE_HEAD) {
            View root1 = LayoutInflater.from(mContext).inflate(R.layout.item_joke_head, parent,false);
            hodler = new BannerViewHolder(root1);
        } else{
            View root2=LayoutInflater.from(mContext).inflate(R.layout.item_joke_normal,parent,false);
            hodler=new ItemViewHolder(root2);

        }
        return hodler;
    }

    @Override//绑定数据
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            BannerViewHolder bannerViewHolder= (BannerViewHolder) holder;
            bannerViewHolder.mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            bannerViewHolder.mBanner.setImages(banner_url);
            bannerViewHolder.mBanner.setDelayTime(2000);

        }else if (holder instanceof ItemViewHolder) {
          //item里面的视图
            ItemViewHolder itemViewHolder= (ItemViewHolder) holder;
            //轮播图有了一位
            itemViewHolder.mTextView.setText(banner_url.get(position-1));
            itemViewHolder.mSimpleDraweeView.setImageURI(banner_url.get(position-1));
        }


    }

    @Override
    public int getItemCount() {
        return banner_url.size() + 1;
    }

    @Override//创建什么类型的viewHolder
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        } else {
            return TYPE_NORMAL;
        }

    }

    //正常的item
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView mSimpleDraweeView;
        private TextView mTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mSimpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.item_joke_sdv);
            mTextView = (TextView) itemView.findViewById(R.id.item_joke_tv);

        }
    }

    //首位的轮播图
    class BannerViewHolder extends RecyclerView.ViewHolder {
        public Banner mBanner;
        public BannerViewHolder(View itemView) {
            super(itemView);
            mBanner = (Banner) itemView.findViewById(R.id.item_joke_banner);
        }
    }


}
