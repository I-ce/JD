package com.six.jd.ui.classify.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.six.jd.R;
import com.six.jd.bean.ClassRightBean;
import com.six.jd.ui.classify.activity.QueryActivity;

import java.util.List;

class MyGridView extends BaseAdapter {
    private Context context;
    private List<ClassRightBean.DataBean.ListBean> lists;

    public MyGridView(Context context, List<ClassRightBean.DataBean.ListBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            view = View.inflate(context, R.layout.right_grid_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.right_grid_text = view.findViewById(R.id.right_grid_Text);
            viewHolder.right_grid_icon = view.findViewById(R.id.right_grid_icon);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.right_grid_icon.setImageURI(lists.get(i).getIcon());
        viewHolder.right_grid_text.setText(lists.get(i).getName());
        view.setOnClickListener(new View.OnClickListener() {

            private int pscid;

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,QueryActivity.class);
                pscid = lists.get(i).getPscid();
                intent.putExtra("edit_name",lists.get(i).getName());
                intent.putExtra("pscid", pscid);
                context.startActivity(intent);
            }
        });

        return view;
    }
    class ViewHolder{
        private TextView right_grid_text;
        private SimpleDraweeView right_grid_icon;

    }
}
