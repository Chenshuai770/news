package com.cs.news1.fragment.fm_adapter.VideoAdater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cs.news1.R;
import com.cs.news1.entry.Web;

import java.util.List;

/**
 * Created by chenshuai on 2016/10/17.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoHolder> {
    private Context mContent;
    private List<Web.ResultBean.DataBean> list;

    public VideoAdapter(Context mContent, List<Web.ResultBean.DataBean> list) {
        this.mContent = mContent;
        this.list = list;
    }

    @Override
    public VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContent).inflate(R.layout.item_vdieo_normal,parent,false);
        return new VideoHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoHolder holder, int position) {
        holder.mTitle.setText(list.get(position).getAuthor_name());
        holder.mContent.setText(list.get(position).getTitle());
        Glide.with(mContent)
                .load(list.get(position).getThumbnail_pic_s())
                .override(100,100)
                .placeholder(R.mipmap.noloading)
                .error(R.mipmap.nosccess)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(holder.mImage);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class VideoHolder extends RecyclerView.ViewHolder {
    public ImageView mImage;
    public TextView mTitle;
    public TextView mContent;

    public VideoHolder(View itemView) {
        super(itemView);
        mImage= (ImageView) itemView.findViewById(R.id.item_video_image);
        mTitle= (TextView) itemView.findViewById(R.id.item_video_title);
        mContent= (TextView) itemView.findViewById(R.id.item_video_content);


    }
}

