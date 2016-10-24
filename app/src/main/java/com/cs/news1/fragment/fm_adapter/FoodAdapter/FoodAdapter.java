package com.cs.news1.fragment.fm_adapter.FoodAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cs.news1.R;
import com.cs.news1.entry.Food;
import com.cs.news1.utils.PicassoUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenshuai on 2016/10/24.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHodler> {
    private List<Food.TngouBean> list = new ArrayList<>();
    private Context context;

    public FoodAdapter(Context context, List<Food.TngouBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);

        return new MyViewHodler(view);

    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        PicassoUtils utils = new PicassoUtils();
        utils.loadImageWithHodler1(context, "http://tnfs.tngou.net/image" + list.get(position).getImg(), 100, 100, holder.image);
        holder.title.setText(list.get(position).getName());
        holder.content.setText(list.get(position).getDescription());
        // Picasso.with(context).load("http://tnfs.tngou.net/image"+list.get(position).getImg()).into(holder.image);
    }
    @Override//这条数据千万别忘记了
    public int getItemCount() {
        return list.size();
    }


    class MyViewHodler extends RecyclerView.ViewHolder {
        TextView title;
        TextView content;
        ImageView image;

        public MyViewHodler(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.item_food_image);
            title = (TextView) itemView.findViewById(R.id.item_food_title);
            content = (TextView) itemView.findViewById(R.id.item_food_content);
        }
    }
}
