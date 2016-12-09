package com.cs.news1.fragment.fm_adpters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cs.news1.R;
import com.cs.news1.entry.News;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHodler>{
    private Context context;
    private List<News.ResultBean.DataBean> mList;
    private OnItemClickLitener mOnItemClickLitener;
    private static final String FILE_NAME = "share";
    private Map<Integer,Boolean> map=new HashMap<>();
    private boolean isClick=false;



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
    public void onBindViewHolder(final NewsHodler holder, final int position) {
        holder.mTitle.setText(mList.get(position).getAuthor_name());
        holder.mContent.setText(mList.get(position).getTitle());
        holder.mTime.setText(mList.get(position).getDate());
        Glide.with(context).load(mList.get(position).getThumbnail_pic_s()).centerCrop().into(holder.mImageview);

        if (map.get(position)!=null) {
            isClick=map.get(position);
            map.remove(position);
            Log.d("TAG",isClick+"");
        }else {
            isClick=false;
        }

        if (isClick) {
            holder.mTitle.setTextColor(Color.parseColor("#808080"));
            holder.mContent.setTextColor(Color.parseColor("#808080"));
            holder.mTime.setTextColor(Color.parseColor("#808080"));
        } else {
            holder.mTitle.setTextColor(Color.parseColor("#141414"));
            holder.mContent.setTextColor(Color.parseColor("#141414"));
            holder.mTime.setTextColor(Color.parseColor("#141414"));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isClick) {
                    map.put(position, true);
                    notifyDataSetChanged();
                }
                if (mOnItemClickLitener != null) {
                    int position1 = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, position1);
                }else{
                    new Throwable(new NullPointerException("mOnItemClickLitener is not null"));
                }
            }
        });



    }

    class NewsHodler extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mContent;
        TextView mTime;
        ImageView mImageview;
        public NewsHodler(View itemView) {
            super(itemView);
            mTitle= (TextView) itemView.findViewById(R.id.tv_news_title);
            mContent= (TextView) itemView.findViewById(R.id.tv_news_content);
            mTime= (TextView) itemView.findViewById(R.id.tv_news_time);
            mImageview= (ImageView) itemView.findViewById(R.id.iv_item_news);
        }
    }

}
