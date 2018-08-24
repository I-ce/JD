package com.six.jd.ui.carts.model;

import com.six.jd.bean.GetOrdersbean;
import com.six.jd.utils.RetrofitUtils;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DDModel {


    public void getData(final DDModelImpl ddModel, String uid) {
        Observable<GetOrdersbean> getOrdersdata =
                RetrofitUtils.getInstance().getServer().GetOrdersdata(uid);
        getOrdersdata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetOrdersbean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(GetOrdersbean getOrdersbean) {
                        ddModel.setOnSuccess(getOrdersbean.getData());
                    }
                });
    }
}
