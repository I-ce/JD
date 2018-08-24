package com.six.jd.ui.classify.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.six.jd.R;
import com.six.jd.bean.ClassRightBean;

import java.util.List;

public class MyClassRightRecycleAdapter extends RecyclerView.Adapter<MyClassRightRecycleAdapter.TextHolder> {

    private Context context;
    private List<ClassRightBean.DataBean> lists;

    public MyClassRightRecycleAdapter(Context context, List<ClassRightBean.DataBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public TextHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.right_layout, null);
        return new TextHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TextHolder holder, int position) {
        holder.right_text_title.setText(lists.get(position).getName());
        holder.right_grid_View.setAdapter(new MyGridView(context,lists.get(position).getList()));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class TextHolder extends RecyclerView.ViewHolder {


        private final GridView right_grid_View;
        private final TextView right_text_title;

        public TextHolder(View itemView) {
            super(itemView);
            right_grid_View = itemView.findViewById(R.id.right_gridview);
            right_text_title = itemView.findViewById(R.id.right_text_Title);
        }
    }

}
