package com.six.jd.ui.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.six.jd.R;
import com.six.jd.bean.HomeBannerBean;
import com.six.jd.ui.classify.activity.ClassAddCartActivity;

import java.util.List;

class MyMiaoshaAdapter extends RecyclerView.Adapter<MyMiaoshaAdapter.TextHolder> {


    private Context context;
    private HomeBannerBean.DataBean.MiaoshaBean list;

    public MyMiaoshaAdapter(Context context, HomeBannerBean.DataBean.MiaoshaBean list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TextHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.home_recycleview_item, null);

        return new TextHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextHolder holder, int position) {
        holder.home_recy_item_iocn.setImageURI(list.getList().get(position).getImages().split("\\|")[0]);
        holder.home_recy_item_price.setText(list.getList().get(position).getPrice());
        holder.home_recy_item_barginprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.home_recy_item_barginprice.setText(list.getList().get(position).getBargainPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ClassAddCartActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.getList().size();
    }

    public class TextHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView home_recy_item_iocn;
        private final TextView home_recy_item_price;
        private final TextView home_recy_item_barginprice;

        public TextHolder(View itemView) {
            super(itemView);
            home_recy_item_iocn = itemView.findViewById(R.id.home_recy_item_iocn);
            home_recy_item_price = itemView.findViewById(R.id.home_recy_item_price);
            home_recy_item_barginprice = itemView.findViewById(R.id.home_recy_item_barginprice);
        }
    }
}
