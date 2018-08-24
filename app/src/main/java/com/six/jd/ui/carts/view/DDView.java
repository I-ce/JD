package com.six.jd.ui.carts.view;

import com.six.jd.bean.GetOrdersbean;

import java.util.List;

public interface DDView {
    void getSuccess(List<GetOrdersbean.DataBean> data);
    void getError();
}
