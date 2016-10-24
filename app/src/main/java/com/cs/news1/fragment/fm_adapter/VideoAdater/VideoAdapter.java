package com.cs.news1.fragment.fm_adapter.VideoAdater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs.news1.R;
import com.cs.news1.entry.Bean;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by chenshuai on 2016/10/17.
 */

public class VideoAdapter extends RecyclerView.Adapter<VideoHolder> {
    private Context mContent;
    private List<Bean.ResultsBean> list;

    public VideoAdapter(Context mContent, List<Bean.ResultsBean> list) {
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
        Bean.ResultsBean resultsBean=list.get(position);
        holder.mSimpleDraweeView.setImageURI(resultsBean.getUrl());
        holder.mTextView.setText(resultsBean.getType());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class VideoHolder extends RecyclerView.ViewHolder {
    public SimpleDraweeView mSimpleDraweeView;
    public TextView mTextView;

    public VideoHolder(View itemView) {
        super(itemView);
        mSimpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.item_video_sdv);
        mSimpleDraweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
        mTextView = (TextView) itemView.findViewById(R.id.item_video_tv);
    }
}

