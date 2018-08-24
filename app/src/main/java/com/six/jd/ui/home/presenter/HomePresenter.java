package com.six.jd.ui.home.presenter;

import com.six.jd.bean.HomeBannerBean;
import com.six.jd.ui.home.model.HomeModel;
import com.six.jd.ui.home.model.HomeModelImpl;
import com.six.jd.ui.home.view.HomeView;

public class HomePresenter implements HomePresenterImpl{

    private HomeView homeView;
    private HomeModel homeModel;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
        homeModel = new HomeModel();
    }

    @Override
    public void onDestory() {
        homeView = null;
    }

    public void getHomeDatas() {

        homeModel.getHomeData(new HomeModelImpl() {
            @Override
            public void setOnSuccess(HomeBannerBean homeBannerBean) {
                homeView.getSuccess(homeBannerBean);
            }

            @Override
            public void setOnError() {

            }
        });
    }


}
