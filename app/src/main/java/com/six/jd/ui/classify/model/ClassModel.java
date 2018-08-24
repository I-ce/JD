package com.six.jd.ui.classify.model;

import android.util.Log;

import com.six.jd.bean.ClassItemBean;
import com.six.jd.bean.ClassLeftBean;
import com.six.jd.bean.ClassRightBean;
import com.six.jd.bean.ClassXQBean;
import com.six.jd.server.ClassGetCartBean;
import com.six.jd.utils.RetrofitUtils;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ClassModel {


    public void getData(final ClassModelImpl classModel) {
        Observable<ClassLeftBean> leftBeanData = RetrofitUtils.getInstance().getServer().ClassLeftBeanData();
        leftBeanData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassLeftBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ClassLeftBean classLeftBean) {
                        classModel.setOnSuccess(classLeftBean.getData());
                    }
                });

    }

    public void getRightData(final ClassModelImpl classModel, String s) {

        Observable<ClassRightBean> rightBeanData = RetrofitUtils.getInstance().getServer().ClassRightBeanData(s);
        rightBeanData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassRightBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("aaaaaaaaaa",e.toString());
                    }

                    @Override
                    public void onNext(ClassRightBean classRightBean) {
                        classModel.setOnSuccess(classRightBean.getData(),"");
                    }
                });
    }

    public void getLieBiaoData(final ClassModelImpl classModel, int pscid) {
        Observable<ClassItemBean> itemData = RetrofitUtils.getInstance().getServer().ClassItemData(String.valueOf(pscid));

        itemData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassItemBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("aaaaaaaa",e.toString());
                    }

                    @Override
                    public void onNext(ClassItemBean classItemBean) {
                        classModel.setOnSuccessQuerry(classItemBean.getData());
                    }
                });

    }

    public void getLieBiaoData(final ClassModelImpl classModel, String edit_name, int page, int sort) {
        Observable<ClassItemBean> itemData = RetrofitUtils.getInstance().getServer().ClassSearchData(edit_name,String.valueOf(page),String.valueOf(sort));
        itemData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassItemBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("aaaaaaaa",e.toString());
                    }

                    @Override
                    public void onNext(ClassItemBean classItemBean) {
                        classModel.setOnSuccessQuerry(classItemBean.getData());
                    }
                });

    }

    public void getXqData(final ClassModelImpl classModel, String pid) {

        Observable<ClassXQBean> xqData = RetrofitUtils.getInstance().getServer().ClassXQData(pid);

        xqData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassXQBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("aaaaaaaa",e.toString());
                    }

                    @Override
                    public void onNext(ClassXQBean classXQBean) {
                        classModel.setOnSuccessXq(classXQBean);
                    }
                });
    }

    public void getGetCartData(final ClassModelImpl classModel, String uid) {
        Observable<ClassGetCartBean> getCartData =
                RetrofitUtils.getInstance().getServer().ClassGetCartData(uid);
        getCartData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ClassGetCartBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("aaaaaaaaaaa","eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+e.toString());
                    }

                    @Override
                    public void onNext(ClassGetCartBean classGetCartBean) {
                        classModel.setOnSuccessCart(classGetCartBean);
                    }
                });

    }
}
