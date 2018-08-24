package com.six.jd.ui.classify.model;

import com.six.jd.bean.ClassItemBean;
import com.six.jd.bean.ClassLeftBean;
import com.six.jd.bean.ClassRightBean;
import com.six.jd.bean.ClassXQBean;
import com.six.jd.server.ClassGetCartBean;

import java.util.List;

public interface ClassModelImpl {
    void setOnSuccess(List<ClassLeftBean.DataBean> data);
    void setOnSuccess(List<ClassRightBean.DataBean> data, String s);
    void setOnSuccessQuerry(List<ClassItemBean.DataBean> dataBeans);

    void setOnError();

    void setOnSuccessXq(ClassXQBean classXQBean);

    void setOnSuccessCart(ClassGetCartBean classGetCartBean);

}
