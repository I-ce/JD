package com.six.jd.ui.carts.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.six.jd.R;
import com.six.jd.bean.GetOrdersbean;
import com.six.jd.ui.classify.activity.DingDanActivity;

import java.util.List;

public class MyDingAdapter extends RecyclerView.Adapter<MyDingAdapter.TextHolder> {

    private Context context;
    private List<GetOrdersbean.DataBean> lists;

    public MyDingAdapter(Context context, List<GetOrdersbean.DataBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public TextHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.dingdanlayout, null);
        return new TextHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextHolder holder, int position) {
        holder.ding_item_price.setText(lists.get(position).getPrice()+"");
        holder.ding_item_time.setText(lists.get(position).getCreatetime());
        holder.ding_item_title.setText(lists.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class TextHolder extends RecyclerView.ViewHolder {

        private final TextView ding_item_price;
        private final TextView ding_item_time;
        private final TextView ding_item_title;

        public TextHolder(View itemView) {
            super(itemView);
            ding_item_price = itemView.findViewById(R.id.ding_item_price);
            ding_item_time = itemView.findViewById(R.id.ding_item_time);
            ding_item_title = itemView.findViewById(R.id.ding_item_title);

        }
    }

}
