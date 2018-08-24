package com.six.jd.ui.home.holder;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.six.jd.R;

public class HomeViewPagerHolder extends RecyclerView.ViewHolder {

    public final ViewPager home_viewpager;
    public final LinearLayout home_viewpager_points;

    public HomeViewPagerHolder(View itemView) {
        super(itemView);
        home_viewpager = itemView.findViewById(R.id.home_viewPager);
        home_viewpager_points = itemView.findViewById(R.id.home_viewPager_points);
    }
}
