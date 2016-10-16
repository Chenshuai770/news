package com.cs.news1.fragment.fm_adapter.NewsAdapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.news1.R;

import java.util.List;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class NewsAdapter extends BaseAdapter {
    private List<String> mList;
    private Context mContext;
    private ViewHolder mViewHolder;
    private LayoutInflater mLayoutInflater;


    public NewsAdapter(Context context, List<String> mList) {


        this.mList = mList;
        this.mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    protected class ViewHolder {
        private ImageView ivNewsImage;
        private TextView tvNewsTitle;
        private TextView tvNwesContent;
        private TextView tvNewsTime;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            mViewHolder = new ViewHolder();
            view = mLayoutInflater.inflate(R.layout.item_news, viewGroup, false);
            mViewHolder.tvNewsTitle = (TextView) view.findViewById(R.id.tv_news_title);
            mViewHolder.tvNewsTitle.setText(mList.get(i));
            view.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }
        return view;


    }


}
