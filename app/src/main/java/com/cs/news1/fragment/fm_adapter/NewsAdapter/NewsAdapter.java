package com.cs.news1.fragment.fm_adapter.NewsAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.cs.news1.R;
import com.cs.news1.entry.Keshi;

import java.util.List;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class NewsAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private List<Keshi.TngouBean> mData;

    public NewsAdapter(Context mContext, List<Keshi.TngouBean> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

//获取父布局的数量
    @Override
    public int getGroupCount() {
        return mData.size();
    }
//获取子布局的数量
    @Override
    public int getChildrenCount(int i) {
        return mData.get(i).getDepartments().size();

    }
//获取父布局对应的item的布局
    @Override
    public Object getGroup(int i) {
        return mData.get(i);
    }
    //获取子布局对应的item的布局
    @Override
    public Object getChild(int i, int i1) {
        return mData.get(i).getDepartments().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }
//子布局对应的id
    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }
    //        分组和子选项是否持有稳定的ID, 就是说底层数据的改变会不会影响到它们。
    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        GroupViewHolder groupViewHolder=null;
        if (view==null) {
            view= LayoutInflater.from(mContext).inflate(R.layout.item_gkeshi,null);
            groupViewHolder=new GroupViewHolder();
            groupViewHolder.tvTitle= (TextView) view.findViewById(R.id.item_gkeshi_title);
            view.setTag(groupViewHolder);

        }else {
            groupViewHolder = (GroupViewHolder) view.getTag();
        }
        groupViewHolder.tvTitle.setText(mData.get(i).getName());
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ChildViewHolder childViewHolder=null;
        if (view==null) {
            view= LayoutInflater.from(mContext).inflate(R.layout.item_ckeshi,null);
            childViewHolder=new ChildViewHolder();
            childViewHolder.tvChildType= (TextView) view.findViewById(R.id.item_ckeshi_title);
            view.setTag(childViewHolder);

        }else {
            childViewHolder = (ChildViewHolder) view.getTag();
        }
        childViewHolder.tvChildType.setText(mData.get(i).getDepartments().get(i1).getName());
        return view;
    }
//子布局是否可以被点击
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    class GroupViewHolder {
        TextView tvTitle;
    }
    class ChildViewHolder {
        TextView tvChildType;

    }
}
