package com.six.jd.ui.home.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.six.jd.R;

public class HomeTuijianHolder extends RecyclerView.ViewHolder {

    public final RecyclerView home_tuijian_recyview;
    public final TextView home_tuijian_title;

    public HomeTuijianHolder(View view) {
        super(view);
        home_tuijian_recyview = view.findViewById(R.id.home_tuijian_recyview);
        home_tuijian_title = view.findViewById(R.id.home_tuijian_title);
    }
}
