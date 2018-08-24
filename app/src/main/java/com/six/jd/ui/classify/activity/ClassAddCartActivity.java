package com.six.jd.ui.classify.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.six.jd.R;
import com.six.jd.base.BaseActivity;
import com.six.jd.bean.ClassItemBean;
import com.six.jd.bean.ClassLeftBean;
import com.six.jd.bean.ClassRightBean;
import com.six.jd.bean.ClassXQBean;
import com.six.jd.server.ClassGetCartBean;
import com.six.jd.ui.classify.adapter.MyClassExpanable;
import com.six.jd.ui.classify.prensenter.ClassPresenter;
import com.six.jd.ui.classify.view.ClassView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClassAddCartActivity extends BaseActivity implements ClassView, View.OnClickListener {

    @BindView(R.id.car_back)
    ImageView carBack;
    @BindView(R.id.car_expanable)
    ExpandableListView carExpanable;
    @BindView(R.id.car_check)
    CheckBox carCheck;
    @BindView(R.id.car_sum)
    TextView carSum;
    @BindView(R.id.car_btn)
    Button carBtn;
    private SharedPreferences sp;
    private ClassPresenter presenter;
    private MyClassExpanable adapter;
    private List<ClassGetCartBean.DataBean> lists;
    private String uid;

    @Override
    protected void initListener() {
            carBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");

        presenter = new ClassPresenter(this);
        presenter.getGetCartData(uid);
        carExpanable.setGroupIndicator(null);
        carCheck.setOnClickListener(this);

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_class_add_cart);
        ButterKnife.bind(this);
    }

    @Override
    public void getSuccess(List<ClassLeftBean.DataBean> data) {

    }

    @Override
    public void getError() {

    }

    @Override
    public void getSuccess(List<ClassRightBean.DataBean> data, String s) {

    }

    @Override
    public void getSuccessQuerry(List<ClassItemBean.DataBean> dataBeans) {

    }

    @Override
    public void getSuccessXq(ClassXQBean classXQBean) {

    }

    @Override
    public void getSuccessCart(ClassGetCartBean classGetCartBean) {
        lists = classGetCartBean.getData();
        Log.i("aaaaaa","----------------------------------"+lists.size()+"");
        adapter = new MyClassExpanable(ClassAddCartActivity.this, classGetCartBean.getData(), new MyClassExpanable.Callback() {
            @Override
            public void GoodCartsLitenster(int countyMoney, int countsNum) {
                carCheck.setText("已选（"+countsNum+"）");
                carSum.setText("￥"+countyMoney);
            }
        });
        carExpanable.setAdapter(adapter);
        int groupCount = carExpanable.getCount();
        for (int i=0; i<groupCount; i++)
        {
            carExpanable.expandGroup(i);
        }
        adapter.setCallbackLong(new MyClassExpanable.CallbackLong() {
            @Override
            public void ItemLongClick() {
                presenter.getGetCartData(uid);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.car_back:
                finish();
                break;
            case R.id.car_check:
                for (int i = 0; i < lists.size(); i++) {
                    for (int j = 0; j < lists.get(i).getList().size(); j++) {
                        if (carCheck.isChecked()){
                            lists.get(i).getList().get(j).setSelected("1");
                        }else{
                            lists.get(i).getList().get(j).setSelected("0");
                        }

                    }
                }
                adapter.changeAllPrice();
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
