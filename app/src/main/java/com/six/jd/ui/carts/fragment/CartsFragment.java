package com.six.jd.ui.carts.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.six.jd.R;
import com.six.jd.base.BaseFragment;
import com.six.jd.bean.ClassDingDanbean;
import com.six.jd.bean.ClassItemBean;
import com.six.jd.bean.ClassLeftBean;
import com.six.jd.bean.ClassRightBean;
import com.six.jd.bean.ClassXQBean;
import com.six.jd.server.ClassGetCartBean;
import com.six.jd.ui.classify.activity.DingDanActivity;
import com.six.jd.ui.classify.adapter.MyClassExpanable;
import com.six.jd.ui.classify.prensenter.ClassPresenter;
import com.six.jd.ui.classify.view.ClassView;
import com.six.jd.utils.RetrofitUtils;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartsFragment extends BaseFragment implements View.OnClickListener,ClassView {



    ImageView carBack;
    ExpandableListView carExpanable;
    CheckBox carCheck;
    TextView carSum;
    Button carBtn;
    private SharedPreferences sp;
    private ClassPresenter presenter;
    private MyClassExpanable adapter;
    private List<ClassGetCartBean.DataBean> lists;
    private String uid;
    private int dcountyMoney;

    public CartsFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initListener() {
        carBack.setOnClickListener(this);
        carCheck.setOnClickListener(this);
        carBtn.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        sp = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        presenter = new ClassPresenter(this);
        presenter.getGetCartData(uid);
        carExpanable.setGroupIndicator(null);
        carCheck.setOnClickListener(this);
    }

    @Override
    protected void initView(View view) {
        carBack = view.findViewById(R.id.cars_back);
        carBtn = view.findViewById(R.id.cars_btn);
        carCheck = view.findViewById(R.id.cars_check);
        carExpanable = view.findViewById(R.id.cars_expanable);
        carSum = view.findViewById(R.id.cars_sum);

    }

    @Override
    protected int privideLayoutId() {
        return R.layout.fragment_carts;
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cars_back:
                getActivity().finish();
                break;
            case R.id.cars_check:
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
            case R.id.cars_btn:
                if (hasCheck()){
                    Observable<ClassDingDanbean> dingDandata = RetrofitUtils.getInstance().getServer().ClassDingDandata(sp.getString("uid", ""), dcountyMoney + "");
                    dingDandata.subscribeOn(Schedulers.io())
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
                                    Intent intent = new Intent(getActivity(),DingDanActivity.class);
                                    getActivity().startActivity(intent);
                                }else{

                                    Toast.makeText(getActivity(),classDingDanbean.getMsg()+"",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                }else{
                    Toast.makeText(getActivity(),"请选中一个商品",Toast.LENGTH_SHORT).show();
                }
                break;
        }
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
        adapter = new MyClassExpanable(getActivity(), classGetCartBean.getData(), new MyClassExpanable.Callback() {
            @Override
            public void GoodCartsLitenster(int countyMoney, int countsNum) {
                dcountyMoney = countyMoney;
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
    public boolean hasCheck() {
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).getList().size(); j++) {
                if (lists.get(i).getList().get(j).getSelected().equals("1")){
                    return true;
                }
            }
        }
        return false;
    }
}
