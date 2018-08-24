package com.six.jd.ui.classify.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.six.jd.R;
import com.six.jd.base.BaseActivity;
import com.six.jd.bean.ClassDingDanbean;
import com.six.jd.bean.ClassItemBean;
import com.six.jd.bean.ClassLeftBean;
import com.six.jd.bean.ClassRightBean;
import com.six.jd.bean.ClassXQBean;
import com.six.jd.server.ClassAddCartBean;
import com.six.jd.server.ClassGetCartBean;
import com.six.jd.ui.classify.prensenter.ClassPresenter;
import com.six.jd.ui.classify.view.ClassView;
import com.six.jd.utils.RetrofitUtils;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class XQActivity extends BaseActivity implements ClassView, View.OnClickListener {


    @BindView(R.id.class_xq_xbanner)
    XBanner classXqXbanner;
    @BindView(R.id.class_xq_title)
    TextView classXqTitle;
    @BindView(R.id.class_xq_price)
    TextView classXqPrice;
    @BindView(R.id.class_xq_btn_chaxun)
    RadioButton classXqBtnChaxun;
    @BindView(R.id.class_xq_btn_add)
    RadioButton classXqBtnAdd;
    @BindView(R.id.class_xq_radio_Group)
    RadioGroup classXqRadioGroup;
    @BindView(R.id.dingdan)
    RadioButton dingdan;
    private ClassPresenter presenter;
    private List<String> strings;
    private SharedPreferences sp;
    private ClassXQBean.DataBean data;

    @Override
    protected void initListener() {
        classXqBtnAdd.setOnClickListener(this);
        classXqBtnChaxun.setOnClickListener(this);
        dingdan.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String pid = intent.getStringExtra("pid");
        presenter = new ClassPresenter(this);
        presenter.getXqData(pid);


    }

    @Override
    protected void initView() {

        setContentView(R.layout.activity_web_view);
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
    public void getSuccessXq(final ClassXQBean classXQBean) {
        data = classXQBean.getData();
        String[] split = classXQBean.getData().getImages().split("\\|");
        strings = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            strings.add(split[i]);
        }
        classXqXbanner.setData(strings, null);
        classXqXbanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(XQActivity.this).load(strings.get(position)).into((ImageView) view);
            }
        });
        classXqPrice.setText(classXQBean.getData().getPrice());
        classXqTitle.setText(classXQBean.getData().getTitle());

    }

    @Override
    public void getSuccessCart(ClassGetCartBean classGetCartBean) {

    }

    @Override
    public void onClick(View view) {
        sp = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        switch (view.getId()) {
            case R.id.class_xq_btn_add:

                if (sp.getBoolean("auto_boolean", false)) {
                    String uid = sp.getString("uid", "");
                    String pid = data.getPid();
                    Observable<ClassAddCartBean> cartData = RetrofitUtils.getInstance().getServer().ClassAddCartData(uid, pid);
                    cartData.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ClassAddCartBean>() {
                                @Override
                                public void onCompleted() {
                                }

                                @Override
                                public void onError(Throwable e) {
                                }

                                @Override
                                public void onNext(ClassAddCartBean classAddCartBean) {
                                    Toast.makeText(XQActivity.this, classAddCartBean.getMsg(), Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(XQActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.class_xq_btn_chaxun:
                if (sp.getBoolean("auto_boolean", false)) {
                    Intent intent = new Intent(XQActivity.this, ClassAddCartActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(XQActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.dingdan:
                Observable<ClassDingDanbean> dandata = RetrofitUtils.getInstance().getServer().ClassDingDandata(sp.getString("uid", ""), data.getPrice());
                dandata.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<ClassDingDanbean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ClassDingDanbean classDingDanbean) {
                                if (classDingDanbean.getMsg().equals("订单创建成功")){
                                    Intent intent = new Intent(XQActivity.this,DingDanActivity.class);
                                    startActivity(intent);
                                }
                            }
                        });
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
