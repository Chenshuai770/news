package com.cs.news1.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by chenshuai on 2016/10/12.
 */

public class BaseFragment extends Fragment {
    private Activity activity;
    public Context getContext(){
        return activity;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity=getActivity();
    }
}
