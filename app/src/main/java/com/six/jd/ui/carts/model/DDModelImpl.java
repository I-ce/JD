package com.six.jd.ui.carts.model;

import com.six.jd.bean.GetOrdersbean;

import java.util.List;

public interface DDModelImpl {
    void setOnSuccess(List<GetOrdersbean.DataBean> data);
    void setOnError();
}
