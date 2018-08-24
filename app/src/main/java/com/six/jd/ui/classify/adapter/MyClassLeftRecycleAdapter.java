package com.six.jd.ui.classify.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.six.jd.R;
import com.six.jd.bean.ClassLeftBean;

import java.util.List;

public class MyClassLeftRecycleAdapter extends RecyclerView.Adapter<MyClassLeftRecycleAdapter.TextHolder> {

    private Context context;
    private List<ClassLeftBean.DataBean> list;

    public MyClassLeftRecycleAdapter(Context context, List<ClassLeftBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TextHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = View.inflate(context, R.layout.class_left_recy_item, null);

        return new TextHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextHolder holder, final int position) {
        holder.class_left_recy_title.setText(list.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cidCallBack.getID(list.get(position).getCid());
            }
        });
    }

    private CidCallBack cidCallBack;

    public void setCidCallBack(CidCallBack cidCallBack) {
        this.cidCallBack = cidCallBack;
    }

    public interface CidCallBack{

        void getID(String id);

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TextHolder extends RecyclerView.ViewHolder {


        private final TextView class_left_recy_title;

        public TextHolder(View itemView) {
            super(itemView);
            class_left_recy_title = itemView.findViewById(R.id.class_left_recy_title);
        }
    }

}
