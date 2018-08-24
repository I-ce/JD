package com.six.jd.ui.home.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.six.jd.R;

public class HomeMiaoshaHolder extends RecyclerView.ViewHolder {

    public final RecyclerView home_miaosha;
    public final TextView home_miaosha_title;

    public HomeMiaoshaHolder(View view) {
        super(view);
        home_miaosha = view.findViewById(R.id.home_miaosha);
        home_miaosha_title = view.findViewById(R.id.home_miaosha_title);
    }
}
