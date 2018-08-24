package com.six.jd.ui.classify.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.six.jd.R;
import com.six.jd.base.BaseActivity;
import com.six.jd.bean.GetOrdersbean;
import com.six.jd.ui.carts.adapter.MyDingAdapter;
import com.six.jd.ui.carts.presenter.DDPresenter;
import com.six.jd.ui.carts.view.DDView;

import java.util.List;

public class DingDanActivity extends BaseActivity implements DDView {


    private XRecyclerView ding_xrecy;
    private DDPresenter presenter;
    private SharedPreferences sp;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        ding_xrecy.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        presenter = new DDPresenter(this);
        presenter.getDatas(sp.getString("uid",""));

    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_ding_dan);
        ding_xrecy = findViewById(R.id.ding_xrecy);
    }

    @Override
    public void getSuccess(List<GetOrdersbean.DataBean> data) {
        ding_xrecy.setAdapter(new MyDingAdapter(DingDanActivity.this,data));
    }

    @Override
    public void getError() {

    }
}
