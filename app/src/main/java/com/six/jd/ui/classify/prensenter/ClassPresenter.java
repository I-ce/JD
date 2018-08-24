package com.six.jd.ui.classify.prensenter;

import com.six.jd.bean.ClassItemBean;
import com.six.jd.bean.ClassLeftBean;
import com.six.jd.bean.ClassRightBean;
import com.six.jd.bean.ClassXQBean;
import com.six.jd.server.ClassGetCartBean;
import com.six.jd.ui.classify.model.ClassModel;
import com.six.jd.ui.classify.model.ClassModelImpl;
import com.six.jd.ui.classify.view.ClassView;

import java.util.List;

public class ClassPresenter implements ClassPresenterImpl{

    private ClassView classView;
    private ClassModel classModel;

    public ClassPresenter(ClassView classView) {
        this.classView = classView;
        classModel = new ClassModel();
    }

    @Override
    public void onDestory() {
        classView = null;
    }

    public void getDatas() {
        classModel.getData(new ClassModelImpl() {
            @Override
            public void setOnSuccess(List<ClassLeftBean.DataBean> data) {
                classView.getSuccess(data);
            }

            @Override
            public void setOnSuccess(List<ClassRightBean.DataBean> data, String s) {

            }

            @Override
            public void setOnSuccessQuerry(List<ClassItemBean.DataBean> dataBeans) {

            }

            @Override
            public void setOnError() {

            }

            @Override
            public void setOnSuccessXq(ClassXQBean classXQBean) {

            }

            @Override
            public void setOnSuccessCart(ClassGetCartBean classGetCartBean) {

            }
        });

    }

    public void getRightDatas(String s) {
        classModel.getRightData(new ClassModelImpl() {
            @Override
            public void setOnSuccess(List<ClassLeftBean.DataBean> data) {

            }

            @Override
            public void setOnSuccess(List<ClassRightBean.DataBean> data, String s) {
                classView.getSuccess(data,s);
            }

            @Override
            public void setOnSuccessQuerry(List<ClassItemBean.DataBean> dataBeans) {

            }

            @Override
            public void setOnError() {

            }

            @Override
            public void setOnSuccessXq(ClassXQBean classXQBean) {

            }

            @Override
            public void setOnSuccessCart(ClassGetCartBean classGetCartBean) {

            }
        }, s);
    }

    public void getLieBiaoData(int pscid) {

        classModel.getLieBiaoData(new ClassModelImpl() {
            @Override
            public void setOnSuccess(List<ClassLeftBean.DataBean> data) {

            }

            @Override
            public void setOnSuccess(List<ClassRightBean.DataBean> data, String s) {

            }

            @Override
            public void setOnSuccessQuerry(List<ClassItemBean.DataBean> dataBeans) {
                classView.getSuccessQuerry(dataBeans);
            }

            @Override
            public void setOnError() {

            }

            @Override
            public void setOnSuccessXq(ClassXQBean classXQBean) {

            }

            @Override
            public void setOnSuccessCart(ClassGetCartBean classGetCartBean) {

            }
        }, pscid);
    }

    public void getLiebIaoData(String edit_name, int page, int sort) {

        classModel.getLieBiaoData(new ClassModelImpl() {
            @Override
            public void setOnSuccess(List<ClassLeftBean.DataBean> data) {

            }

            @Override
            public void setOnSuccess(List<ClassRightBean.DataBean> data, String s) {

            }

            @Override
            public void setOnSuccessQuerry(List<ClassItemBean.DataBean> dataBeans) {
                classView.getSuccessQuerry(dataBeans);
            }

            @Override
            public void setOnError() {

            }

            @Override
            public void setOnSuccessXq(ClassXQBean classXQBean) {

            }

            @Override
            public void setOnSuccessCart(ClassGetCartBean classGetCartBean) {

            }
        }, edit_name, page,sort);
    }

    public void getXqData(String pid) {

        classModel.getXqData(new ClassModelImpl() {
            @Override
            public void setOnSuccess(List<ClassLeftBean.DataBean> data) {

            }

            @Override
            public void setOnSuccess(List<ClassRightBean.DataBean> data, String s) {

            }

            @Override
            public void setOnSuccessQuerry(List<ClassItemBean.DataBean> dataBeans) {

            }

            @Override
            public void setOnError() {

            }

            @Override
            public void setOnSuccessXq(ClassXQBean classXQBean) {
                classView.getSuccessXq(classXQBean);
            }

            @Override
            public void setOnSuccessCart(ClassGetCartBean classGetCartBean) {

            }
        }, pid);
    }

    public void getGetCartData(String uid) {
        classModel.getGetCartData(new ClassModelImpl() {
            @Override
            public void setOnSuccess(List<ClassLeftBean.DataBean> data) {

            }

            @Override
            public void setOnSuccess(List<ClassRightBean.DataBean> data, String s) {

            }

            @Override
            public void setOnSuccessQuerry(List<ClassItemBean.DataBean> dataBeans) {

            }

            @Override
            public void setOnError() {

            }

            @Override
            public void setOnSuccessXq(ClassXQBean classXQBean) {

            }

            @Override
            public void setOnSuccessCart(ClassGetCartBean classGetCartBean) {
                classView.getSuccessCart(classGetCartBean);
            }
        }, uid);

    }
}
