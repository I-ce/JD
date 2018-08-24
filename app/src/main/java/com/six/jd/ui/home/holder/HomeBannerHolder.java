package com.six.jd.ui.home.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.six.jd.R;
import com.stx.xhb.xbanner.XBanner;

public class HomeBannerHolder extends RecyclerView.ViewHolder {

    public final XBanner home_xbanner;

    public HomeBannerHolder(View itemView) {
        super(itemView);
        home_xbanner = itemView.findViewById(R.id.home_xbanner);
    }
}
