package com.six.jd.ui.mine.model;


import com.six.jd.bean.LoginBean;
import com.six.jd.bean.RegBean;
import com.six.jd.utils.RetrofitUtils;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AModel{
    //登陆
    public void getLoginData(final IloginModel iloginModel, String base, final String name, final String pwd){
        Observable<LoginBean> observable = RetrofitUtils.getInstance().getServer().login(name, pwd);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        iloginModel.success(loginBean);
                    }
                });
    }
    //注册
    public void getRegistData(final IregistModel iregistModel, String base, final String name1, String pwd1){
        //实例化Retrofit
        Observable<RegBean> observable = RetrofitUtils.getInstance().getServer().regist(name1, pwd1);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RegBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                    @Override
                    public void onNext(RegBean regBean) {
                        iregistModel.successZc(regBean);
                    }
                });
    }
}
