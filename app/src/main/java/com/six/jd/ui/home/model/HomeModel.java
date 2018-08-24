package com.six.jd.ui.home.model;

import android.util.Log;

import com.six.jd.bean.HomeBannerBean;
import com.six.jd.utils.RetrofitUtils;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeModel {


    public void getHomeData(final HomeModelImpl homeModelImpl) {

        Observable<HomeBannerBean> homeBannerData = RetrofitUtils.getInstance().getServer().homeBannerData();
        homeBannerData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBannerBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("aaaaaaaaaaaaaa",e.toString());
                    }

                    @Override
                    public void onNext(HomeBannerBean homeBannerBean) {
                        homeModelImpl.setOnSuccess(homeBannerBean);
                    }
                });

    }

}
