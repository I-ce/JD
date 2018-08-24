package com.six.jd.ui.mine.presenter;

import com.six.jd.bean.LoginBean;
import com.six.jd.bean.RegBean;
import com.six.jd.ui.mine.activity.LoginActivity;
import com.six.jd.ui.mine.model.AModel;
import com.six.jd.ui.mine.model.IloginModel;
import com.six.jd.ui.mine.model.IregistModel;
import com.six.jd.ui.mine.view.IloginView;
import com.six.jd.ui.mine.view.IregistView;

public class APresenter{
    private IloginView iloginView;
    private AModel aModel;
    private IregistView iregistView;
    public APresenter(LoginActivity iloginView) {
        this.iloginView=iloginView;
        this.aModel=new AModel();
    }
    public APresenter(IregistView iregistView) {
        this.iregistView=iregistView;
        this.aModel=new AModel();
    }
    public void getLoginDatas(String base, final String name, final String pwd){
        aModel.getLoginData(new IloginModel() {
            @Override
            public void success(LoginBean loginBean) {
                iloginView.callback(loginBean);
            }
        },base,name,pwd);
    }
    public void getRegistDatas(String base, final String name1, final String pwd1){
        aModel.getRegistData(new IregistModel() {
            @Override
            public void successZc(RegBean regBean) {
                iregistView.callbackZc(regBean);
            }
        },base,name1,pwd1);
    }
}
