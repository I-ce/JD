package com.six.jd.ui.home.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.six.jd.R;
import com.six.jd.base.BaseFragment;
import com.six.jd.bean.ClassItemBean;
import com.six.jd.bean.ClassLeftBean;
import com.six.jd.bean.ClassRightBean;
import com.six.jd.bean.HomeBannerBean;
import com.six.jd.bean.QuerryMessage;
import com.six.jd.ui.classify.activity.QueryActivity;
import com.six.jd.ui.classify.prensenter.ClassPresenter;
import com.six.jd.ui.classify.view.ClassView;
import com.six.jd.ui.home.adapter.MyHomeBannerAdapter;
import com.six.jd.ui.home.presenter.HomePresenter;
import com.six.jd.ui.home.view.HomeView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class HomeFragment extends BaseFragment implements HomeView{
    private String seart_name;

    private EditText home__et_message;
    private ImageView home__iv_sou;

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestory();
    }

    private List<String> imagesUrl;
    private XRecyclerView home_xrecyview;
    private HomePresenter presenter;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        home_xrecyview.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        presenter = new HomePresenter(this);
        presenter.getHomeDatas();
        home__iv_sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seart_name = home__et_message.getText().toString();
                Intent intent = new Intent(getActivity(), QueryActivity.class);
                intent.putExtra("edit_name",seart_name);
               getActivity().startActivity(intent);
            }
        });

    }

    @Override
    protected void initView(View view) {
        home__et_message = view.findViewById(R.id.home__et_message);
        home__iv_sou = view.findViewById(R.id.home__iv_sou);
        home_xrecyview = view.findViewById(R.id.home_xrecyview);
        home_xrecyview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                home_xrecyview.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                home_xrecyview.refreshComplete();
            }
        });
    }

    @Override
    protected int privideLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void getSuccess(HomeBannerBean homeBannerBean) {
        home_xrecyview.setAdapter(new MyHomeBannerAdapter(getActivity(),homeBannerBean.getData()));

    }


    @Override
    public void getError() {

    }
}
