package com.six.jd.ui.carts.presenter;

import com.six.jd.bean.GetOrdersbean;
import com.six.jd.ui.carts.model.DDModel;
import com.six.jd.ui.carts.model.DDModelImpl;
import com.six.jd.ui.carts.view.DDView;
import com.six.jd.ui.classify.activity.DingDanActivity;

import java.util.List;

public class DDPresenter implements DDPresenterImpl{

    private DDView ddView;
    private DDModel ddModel;

    public DDPresenter(DDView ddView) {
        this.ddView = ddView;
        ddModel = new DDModel();
    }


    @Override
    public void onDestory() {

    }

    public void getDatas(String uid) {


        ddModel.getData(new DDModelImpl() {
            @Override
            public void setOnSuccess(List<GetOrdersbean.DataBean> data) {
                ddView.getSuccess(data);
            }

            @Override
            public void setOnError() {

            }
        }, uid);
    }
}
