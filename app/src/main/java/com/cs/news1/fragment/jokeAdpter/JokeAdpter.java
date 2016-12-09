package com.cs.news1.fragment.jokeAdpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs.news1.R;
import com.cs.news1.entry.Jokes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by chenshuai on 2016/11/29.
 */

public class JokeAdpter  extends RecyclerView.Adapter<JokeAdpter.JokeHodler> {
    private Context context;
    private List<Jokes.ResultBean.DataBean> mList;
    private String format1;

    public JokeAdpter(Context context, List<Jokes.ResultBean.DataBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public JokeHodler onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_joke,parent,false);
        JokeHodler jokeHodler = new JokeHodler(view);
        return jokeHodler;
    }

    @Override
    public void onBindViewHolder(JokeHodler holder, int position) {
        holder.mTvContent.setText(mList.get(position).getContent());

        String updatetime = mList.get(position).getUpdatetime();
        long unixtime =(mList.get(position).getUnixtime()) *1000;
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(unixtime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format2 = format.format(calendar.getTime());
        Log.d("TTT",format2);

        try {
            Date date = format.parse(updatetime);
            Log.d("GGG",date+"");
            format1 = format.format(date);
            Log.d("TTT", format1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.mTvTime.setText(format1);



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class JokeHodler extends RecyclerView.ViewHolder {
        TextView mTvContent;
        TextView mTvTime;

        public JokeHodler(View itemView) {
            super(itemView);
            mTvContent= (TextView) itemView.findViewById(R.id.tv_joke_content);
            mTvTime= (TextView) itemView.findViewById(R.id.tv_joke_time);
        }
    }
}
