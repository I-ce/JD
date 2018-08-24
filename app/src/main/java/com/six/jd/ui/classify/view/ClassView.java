package com.six.jd.ui.classify.view;

import com.six.jd.bean.ClassItemBean;
import com.six.jd.bean.ClassLeftBean;
import com.six.jd.bean.ClassRightBean;
import com.six.jd.bean.ClassXQBean;
import com.six.jd.server.ClassGetCartBean;

import java.util.List;

public interface ClassView {
    void getSuccess(List<ClassLeftBean.DataBean> data);

    void getError();

    void getSuccess(List<ClassRightBean.DataBean> data, String s);

    void getSuccessQuerry(List<ClassItemBean.DataBean> dataBeans);

    void getSuccessXq(ClassXQBean classXQBean);

    void getSuccessCart(ClassGetCartBean classGetCartBean);

}
