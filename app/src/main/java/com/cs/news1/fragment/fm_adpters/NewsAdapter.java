package com.cs.news1.fragment.fm_adpters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cs.news1.R;
import com.cs.news1.entry.News;

import java.util.List;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHodler>{
    private Context context;
    private List<News.ResultBean.DataBean> mList;
    private OnItemClickLitener mOnItemClickLitener;
    public interface OnItemClickLitener{
        void onItemClick(View view,int position);
    }
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener=mOnItemClickLitener;
    }

    public NewsAdapter(Context context, List<News.ResultBean.DataBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public NewsHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent,false);
        NewsHodler hodler = new NewsHodler(view);
        return hodler;
    }

    @Override
    public void onBindViewHolder(final NewsHodler holder, int position) {
        holder.mTitle.setText(mList.get(position).getAuthor_name());
        holder.mContent.setText(mList.get(position).getTitle());
        holder.mTime.setText(mList.get(position).getDate());
        Glide.with(context).load(mList.get(position).getThumbnail_pic_s()).centerCrop().into(holder.mImageview);
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position1 = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView,position1);
                }
            });
        }
    }

    class NewsHodler extends RecyclerView.ViewHolder {
        public TextView mTitle;
        public TextView mContent;
        public TextView mTime;
        public ImageView mImageview;
        public NewsHodler(View itemView) {
            super(itemView);
            mTitle= (TextView) itemView.findViewById(R.id.tv_news_title);
            mContent= (TextView) itemView.findViewById(R.id.tv_news_content);
            mTime= (TextView) itemView.findViewById(R.id.tv_news_time);
            mImageview= (ImageView) itemView.findViewById(R.id.iv_item_news);
        }
    }

}
