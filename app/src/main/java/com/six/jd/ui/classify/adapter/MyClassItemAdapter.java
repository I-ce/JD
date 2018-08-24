package com.six.jd.ui.classify.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.six.jd.R;
import com.six.jd.bean.ClassItemBean;
import com.six.jd.ui.classify.activity.QueryActivity;

import java.util.List;

public class MyClassItemAdapter extends RecyclerView.Adapter<MyClassItemAdapter.TextHolder> {


    private Context context;
    private List<ClassItemBean.DataBean> list;

    public MyClassItemAdapter(Context context, List<ClassItemBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TextHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.class_fenlei_item, null);

        return new TextHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextHolder holder, final int position) {
        holder.classs_fenlei_item_icon.setImageURI(list.get(position).getImages().split("\\|")[0]);
        holder.class_fenlei_item_title.setText(list.get(position).getTitle());
        holder.class_fenlei_item_price.setText("￥"+list.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callClassItemBack.getUrl(list.get(position).getPid());
            }
        });

    }

    private CallClassItemBack callClassItemBack;

    public void setCallClassItemBack(CallClassItemBack callClassItemBack) {
        this.callClassItemBack = callClassItemBack;
    }

    public interface CallClassItemBack{
        void getUrl(String url);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TextHolder extends RecyclerView.ViewHolder {

        private final TextView class_fenlei_item_title;
        private final SimpleDraweeView classs_fenlei_item_icon;
        private final TextView class_fenlei_item_price;

        public TextHolder(View itemView) {
            super(itemView);
            class_fenlei_item_title = itemView.findViewById(R.id.class_fenlei_item_title);
            classs_fenlei_item_icon = itemView.findViewById(R.id.classs_fenlei_item_icon);
            class_fenlei_item_price = itemView.findViewById(R.id.class_fenlei_item_price);
        }
    }
}
