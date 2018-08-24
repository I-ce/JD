package com.six.jd.ui.classify.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.common.streams.LimitedInputStream;
import com.facebook.drawee.view.SimpleDraweeView;
import com.six.jd.R;
import com.six.jd.bean.ClassdeleteCartBean;
import com.six.jd.server.ClassGetCartBean;
import com.six.jd.server.ClassUpdateBean;
import com.six.jd.ui.classify.view.SumLayout;
import com.six.jd.utils.RetrofitUtils;

import org.w3c.dom.Text;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyClassExpanable extends BaseExpandableListAdapter {

    private Context context;
    private List<ClassGetCartBean.DataBean> lists;
    private int countsMoney;
    private int countsNum;
    private Callback callback;
    private SharedPreferences sp;
    private ClassGetCartBean.DataBean.ListBean listBean;
    private AlertDialog.Builder bu;
    private CallbackLong callbackLong;

    public void setCallbackLong(CallbackLong callbackLong) {
        this.callbackLong = callbackLong;
    }

    public MyClassExpanable(Context context, List<ClassGetCartBean.DataBean> lists, Callback callback) {
        this.context = context;
        this.lists = lists;
        this.callback = callback;
    }


    @Override
    public int getGroupCount() {
        return lists.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return lists.get(i).getList().size();
    }

    @Override
    public Object getGroup(int i) {
        return lists.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return lists.get(i).getList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ViewParentHolder holder = null;
        if (view == null){
            view = View.inflate(context, R.layout.cart_parent_item,null);
            holder = new ViewParentHolder();
            holder.parent_title = view.findViewById(R.id.cart_parent_title);
            view.setTag(holder);
        }else{
            holder = (ViewParentHolder) view.getTag();
        }
        holder.parent_title.setText(lists.get(i).getSellerName());

        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        sp = context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        ViewChildHolder holder = null;
        if (view == null){
            view = View.inflate(context,R.layout.cart_child_item,null);
            holder = new ViewChildHolder();
            holder.child_check = view.findViewById(R.id.cart_child_check);
            holder.child_icon = view.findViewById(R.id.cart_child_icon);
            holder.child_price = view.findViewById(R.id.cart_child_price);
            holder.child_title = view.findViewById(R.id.cart_child_title);
            holder.child_num = view.findViewById(R.id.cart_child_num);
            holder.sumLayout = view.findViewById(R.id.sum_layout);
            view.setTag(holder);
        }else{
            holder = (ViewChildHolder) view.getTag();
        }
        holder.child_title.setText(lists.get(i).getList().get(i1).getTitle());
        holder.child_price.setText("￥"+lists.get(i).getList().get(i1).getPrice());
        holder.child_icon.setImageURI(lists.get(i).getList().get(i1).getImages().split("\\|")[0]);
        holder.child_num.setText("x"+lists.get(i).getList().get(i1).getNum());
        final ViewChildHolder finalHolder = holder;
        String count = finalHolder.sumLayout.getCount();
        finalHolder.sumLayout.setCount(lists.get(i).getList().get(i1).getNum()+"");
        listBean = lists.get(i).getList().get(i1);
        holder.sumLayout.setOnDownSumLayoutListener(new SumLayout.OnDownSumLayouListener() {
            @Override
            public void onDownSumLayout() {
                String count = finalHolder.sumLayout.getCount();

                lists.get(i).getList().get(i1).setNum(count);
                changeData(listBean.getSelected(),listBean.getPid(),listBean.getNum(),lists.get(i).getSellerid());
                notifyDataSetChanged();
            }
        });
        if (lists.get(i).getList().get(i1).getSelected().equals("1")){
            holder.child_check.setChecked(true);
        }else{
            holder.child_check.setChecked(false);
        }
        final ViewChildHolder finalHolder1 = holder;
        holder.child_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finalHolder1.child_check.isChecked()){
                    lists.get(i).getList().get(i1).setSelected("1");
                }else{
                    lists.get(i).getList().get(i1).setSelected("0");
                }
                changeAllPrice();
                listBean = lists.get(i).getList().get(i1);
                changeData(listBean.getSelected(), listBean.getPid(), listBean.getNum(),lists.get(i).getSellerid());
            }
        });
        changeAllPrice();
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                bu = new AlertDialog.Builder(context)
                .setTitle("删除")
                .setMessage("确定要删除这个商品吗")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int l) {
                        Toast.makeText(context,listBean.getPid()+"",Toast.LENGTH_SHORT).show();
                        Observable<ClassdeleteCartBean> cartData = RetrofitUtils.getInstance().getServer().ClassdeleteCartData(sp.getString("uid", ""), lists.get(i).getList().get(i1).getPid());
                        cartData.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Observer<ClassdeleteCartBean>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(ClassdeleteCartBean classdeleteCartBean) {
                                        //Toast.makeText(context,classdeleteCartBean.getMsg(),Toast.LENGTH_SHORT).show();
                                        callbackLong.ItemLongClick();
                                    }
                                });
                    }
                });
                bu.create().show();

                return false;
            }
        });
        return view;
    }
    private void changeData(String selected, String pid, String num, String sellerid) {
        sp = context.getSharedPreferences("userInfo",Context.MODE_PRIVATE);
        Observable<ClassUpdateBean> updateData = RetrofitUtils.getInstance().getServer().ClassUpdateData(sp.getString("uid", ""), sellerid, pid, selected, num);
        updateData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassUpdateBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ClassUpdateBean classUpdateBean) {
                        Toast.makeText(context,classUpdateBean.getMsg(),Toast.LENGTH_SHORT).show();

                    }
                });

    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        changeAllPrice();
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public void changeAllPrice() {
        countsMoney = 0;
        countsNum = 0;
        if (lists == null){
            return;
        }
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).getList().size(); j++) {
                listBean = lists.get(i).getList().get(j);
                if (listBean.getSelected().equals("1")){
                    countsMoney += (Integer.parseInt(listBean.getNum())*Double.parseDouble(listBean.getPrice()));
                    countsNum+=Integer.parseInt(listBean.getNum());
                }
            }
        }
    callback.GoodCartsLitenster(countsMoney,countsNum);
    }

    class ViewParentHolder{
        private TextView parent_title;
    }

    class ViewChildHolder {
        private TextView child_title;
        private CheckBox child_check;
        private SimpleDraweeView child_icon;
        private TextView child_price;
        private TextView child_num;
        private SumLayout sumLayout;
    }
    public interface Callback {
        void GoodCartsLitenster(int countyMoney, int countsNum);
    }

    public interface CallbackLong{
        void ItemLongClick();
    }
}
